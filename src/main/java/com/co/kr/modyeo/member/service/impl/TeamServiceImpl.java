package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import com.co.kr.modyeo.member.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.member.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.member.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.domain.entity.Team;
import com.co.kr.modyeo.member.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.member.repository.TeamCategoryRepository;
import com.co.kr.modyeo.member.repository.TeamRepository;
import com.co.kr.modyeo.member.service.TeamService;
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

    @Override
    @Transactional
    public Team createTeam(TeamRequest teamRequest) {
        overlapTeamCheck(teamRequest);
        Team team = teamRequest.toEntity();
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

        return team;
    }

    @Override
    public Slice<TeamResponse> getTeam(TeamSearch teamSearch) {
        PageRequest page = PageRequest.of(teamSearch.getOffset(), teamSearch.getLimit(), teamSearch.getDirection(), teamSearch.getOrderBy());
        Slice<Team> teams = teamRepository.searchTeam(teamSearch,page);
        return teams.map(TeamResponse::toRes);
    }

    @Override
    public Team updateTeam(TeamRequest teamRequest) {
        overlapTeamCheck(teamRequest);
        Team findTeam = teamRepository.findTeamById(teamRequest.getId()).orElseThrow(() -> ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                .build());

        findTeam.changeTeamInfo(teamRequest.getName());
        return findTeam;
    }

    @Override
    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                .build());

        teamRepository.delete(team);
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
