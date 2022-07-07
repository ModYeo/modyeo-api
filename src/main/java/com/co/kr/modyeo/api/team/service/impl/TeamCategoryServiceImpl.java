package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.api.team.service.TeamCategoryService;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.team.repository.TeamCategoryRepository;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamCategoryServiceImpl implements TeamCategoryService {
    private final TeamRepository teamRepository;
    private final CategoryRepository categoryRepository;
    private final TeamCategoryRepository teamCategoryRepository;

    @Override
    @Transactional
    public TeamCategory createTeamCategory(Long teamId, Long categoryId) {
        Team team = findTeam(teamId);
        Category category = findCategory(categoryId);

        TeamCategory teamCategory = TeamCategory.of()
                .team(team)
                .category(category)
                .build();

        teamCategoryRepository.save(teamCategory);
        return teamCategory;
    }

    @Override
    @Transactional
    public void deleteTeamCategory(Long teamCategoryId) {
        TeamCategory teamCategory = teamCategoryRepository.findById(teamCategoryId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(null)
                        .errorMessage(null)
                        .build()
        );

        teamCategoryRepository.delete(teamCategory);
    }

    private Team findTeam(Long teamId){
        return teamRepository.findById(teamId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                        .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                        .build());
    }

    private Category findCategory(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .build());
    }
}
