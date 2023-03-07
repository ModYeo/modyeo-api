package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.member.auth.domain.dto.MemberJoinDto;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberActiveArea;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.SomeoneTeamSearch;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.TeamActiveArea;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
import com.co.kr.modyeo.api.team.repository.TeamActivAreaRepository;
import com.co.kr.modyeo.api.team.repository.TeamCategoryRepository;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.api.team.service.TeamService;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamCategoryRepository teamCategoryRepository;
    private final CrewRepository crewRepository;
    private final MemberRepository memberRepository;

    private final EmdAreaRepository emdAreaRepository;

    private final TeamActivAreaRepository teamActivAreaRepository;

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Long createTeam(TeamCreateRequest teamCreateRequest) {
        overlapTeamCheck(teamCreateRequest);

        Long memberId = SecurityUtil.getCurrentMemberId();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("")
                        .errorMessage("")
                        .build());

        Team team = TeamCreateRequest.toEntity(teamCreateRequest);
        team = teamRepository.save(team);

        if(!teamCreateRequest.getCategoryIdList().isEmpty()){
            saveTeamCategory(team,teamCreateRequest);
        }

        if (!teamCreateRequest.getEmdAreaList().isEmpty()){
            saveTeamActiveArea(team, teamCreateRequest);
        }

        saveOwner(member,team);

        return team.getId();
    }

    private void saveOwner(Member member,Team team){
        Crew crew = Crew.createOwnerBuilder()
                .member(member)
                .team(team)
                .build();

        crewRepository.save(crew);
    }

    private void saveTeamActiveArea(Team team, TeamCreateRequest teamCreateRequest) {
        List<EmdArea> emdAreaList = emdAreaRepository.findByEmdIdList(TeamCreateRequest.EmdAreaDto.getIdList(teamCreateRequest.getEmdAreaList()));

        List<TeamActiveArea> teamActiveAreaList = emdAreaList.stream().map(emdArea -> {
                    TeamCreateRequest.EmdAreaDto emdAreaDto = teamCreateRequest.getEmdAreaDto(emdArea.getId());
                    return TeamActiveArea.createTeamAreaBuilder()
                            .team(team)
                            .emdArea(emdArea)
                            .limitMeters(emdAreaDto.getLimitMeters())
                            .build();
                })
                .collect(Collectors.toList());

        teamActivAreaRepository.saveAll(teamActiveAreaList);
    }

    private void saveTeamCategory(Team team, TeamCreateRequest teamCreateRequest){
        List<Category> categories = categoryRepository.findByCategoryIds(teamCreateRequest.getCategoryIdList());

        List<TeamCategory> crewCategories = categories.stream().map(category -> TeamCategory.of()
                .category(category)
                .team(team)
                .build()).collect(Collectors.toList());

        teamCategoryRepository.saveAll(crewCategories);
    }

    @Override
    public Slice<TeamResponse> getTeams(TeamSearch teamSearch) {
        PageRequest page = PageRequest.of(teamSearch.getOffset(), teamSearch.getLimit(), teamSearch.getDirection(), teamSearch.getOrderBy());
        Slice<Team> teams = teamRepository.searchTeam(teamSearch,page);
        return teams.map(TeamResponse::toDto);
    }

    public Slice<TeamResponse> getSomeoneTeam(SomeoneTeamSearch someoneTeamSearch){
        PageRequest page = PageRequest.of(someoneTeamSearch.getOffset(), someoneTeamSearch.getLimit(),
                                        someoneTeamSearch.getDirection(), someoneTeamSearch.getOrderBy()
                                         );
        Slice<Team> memberTeam = teamRepository.searchSomeoneTeam(someoneTeamSearch, page);
        return memberTeam.map(TeamResponse::toDto);
    }

    @Override
    @Transactional
    public Long updateTeam(TeamUpdateRequest teamUpdateRequest) {
        overlapTeamCheck(teamUpdateRequest);
        Team findTeam = teamRepository.findTeamById(teamUpdateRequest.getTeamId()).orElseThrow(() -> ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                .build());

        findTeam.changeTeamInfo(teamUpdateRequest.getName(), teamUpdateRequest.getProfilePath(), teamUpdateRequest.getDescription());
        return findTeam.getId();
    }

    @Override
    @Transactional
    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                .build());

        teamRepository.delete(team);
    }

    @Override
    public TeamDetail getTeam(Long teamId) {
        return TeamDetail.toDto(teamRepository.findById(teamId).orElseThrow(() -> ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                .build()));
    }

    @Override
    public List<TeamResponse> getRecommendTeams(Long emdId, List<Long> categoryIdList) {
        List<Team> recommendTeams = teamRepository.getRecommendTeams(emdId, categoryIdList);
        return recommendTeams.stream()
                .map(TeamResponse::toDto)
                .collect(Collectors.toList());
    }

    private void overlapTeamCheck(TeamCreateRequest teamCreateRequest){
        Team findTeam = teamRepository.findByName(teamCreateRequest.getName());
        if (findTeam != null){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(TeamErrorCode.OVERLAP_TEAM.getCode())
                    .errorMessage(TeamErrorCode.OVERLAP_TEAM.getMessage())
                    .build();

        }
    }

    private void overlapTeamCheck(TeamUpdateRequest teamCreateRequest){
        Team findTeam = teamRepository.findByName(teamCreateRequest.getName());
        if (findTeam != null){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(TeamErrorCode.OVERLAP_TEAM.getCode())
                    .errorMessage(TeamErrorCode.OVERLAP_TEAM.getMessage())
                    .build();

        }
    }
}
