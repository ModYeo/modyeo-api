package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
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
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Team createTeam(TeamRequest teamRequest) {
        overlapTeamCheck(teamRequest);

        String memberEmail = SecurityUtil.getCurrentMemberId();

        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(()->ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("")
                        .errorMessage("")
                        .build());

        Team team = TeamRequest.toEntity(teamRequest);
        team = teamRepository.save(team);

        if(!teamRequest.getCategoryDtoList().isEmpty()){
            List<Category> categories = teamRequest
                    .getCategoryDtoList().stream()
                    .map(TeamRequest.CategoryDto::toEntity)
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

        return team;
    }

    @Override
    public Slice<TeamResponse> getTeams(TeamSearch teamSearch) {
        PageRequest page = PageRequest.of(teamSearch.getOffset(), teamSearch.getLimit(), teamSearch.getDirection(), teamSearch.getOrderBy());
        Slice<Team> teams = teamRepository.searchTeam(teamSearch,page);
        return teams.map(TeamResponse::toRes);
    }

    @Override
    @Transactional
    public Team updateTeam(TeamRequest teamRequest) {
        overlapTeamCheck(teamRequest);
        Team findTeam = teamRepository.findTeamById(teamRequest.getTeam_id()).orElseThrow(() -> ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                .build());

        findTeam.changeTeamInfo(teamRequest.getName(), teamRequest.getProfilePath());
        return findTeam;
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
    public TeamResponse getMyTeam(String email) {
        return teamRepository.findMyTeam(email);
    }

    private void overlapTeamCheck(TeamRequest teamRequest){
        Team findTeam = teamRepository.findByName(teamRequest.getName());
        if (findTeam != null){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(TeamErrorCode.OVERLAP_TEAM.getCode())
                    .errorMessage(TeamErrorCode.OVERLAP_TEAM.getMessage())
                    .build();

        }
    }
}
