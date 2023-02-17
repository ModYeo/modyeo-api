package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.SomeoneTeamSearch;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
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

        if(!teamCreateRequest.getCategoryDtoList().isEmpty()){
            List<Category> categories = teamCreateRequest
                    .getCategoryDtoList().stream()
                    .map(TeamCreateRequest.CategoryDto::toEntity)
                    .collect(Collectors.toList());

            Team finalTeam = team;
            List<TeamCategory> crewCategories = categories.stream().map(category -> TeamCategory.of()
                    .category(category)
                    .team(finalTeam)
                    .build()).collect(Collectors.toList());

            teamCategoryRepository.saveAll(crewCategories);
        }

        Crew crew = Crew.createOwnerBuilder()
                .member(member)
                .team(team)
                .build();

        crewRepository.save(crew);

        return team.getId();
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
