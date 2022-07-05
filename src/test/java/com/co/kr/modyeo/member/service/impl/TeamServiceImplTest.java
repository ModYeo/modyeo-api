package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.team.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.category.domain.entity.Category;
import com.co.kr.modyeo.team.domain.entity.Team;
import com.co.kr.modyeo.team.domain.entity.link.TeamCategory;
import com.co.kr.modyeo.team.repository.TeamCategoryRepository;
import com.co.kr.modyeo.team.repository.TeamRepository;
import com.co.kr.modyeo.team.service.TeamService;
import com.co.kr.modyeo.team.service.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamCategoryRepository teamCategoryRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        teamService = new TeamServiceImpl(teamRepository, teamCategoryRepository);
    }

    private TeamRequest.CategoryDto categoryDto = TeamRequest.CategoryDto.builder()
            .id(1L)
            .name("test category")
            .build();

    private TeamRequest crewCreateRequest = TeamRequest.builder()
            .name("test crew")
            .categoryDtoList(new ArrayList<>(List.of(categoryDto)))
            .build();

    @Test
    @DisplayName("crew 생성 테스트1")
    void crewCreate() {
        Team team = crewCreateRequest.toEntity();
        TeamCategory teamCategory = TeamCategory.of()
                .id(1L)
                .crew(team)
                .category(categoryDto.toEntity())
                .build();

        given(teamRepository.save(any())).willReturn(team);
        given(teamCategoryRepository.save(any())).willReturn(teamCategory);

        team = teamRepository.save(team);

        if(!crewCreateRequest.getCategoryDtoList().isEmpty()) {
            for (TeamRequest.CategoryDto categoryDto : crewCreateRequest.getCategoryDtoList()) {
                Category category = categoryDto.toEntity();
                TeamCategory teamCategory1 = TeamCategory.of()
                        .crew(team)
                        .category(category)
                        .build();
                teamCategoryRepository.save(teamCategory1);
            }
        }
    }

    @Test
    @DisplayName("crew 생성 테스트2")
    void crewCreate2() {
        Team team = crewCreateRequest.toEntity();
        TeamCategory teamCategory = TeamCategory.of()
                .id(1L)
                .crew(team)
                .category(categoryDto.toEntity())
                .build();

        given(teamRepository.save(any())).willReturn(team);
        given(teamCategoryRepository.save(any())).willReturn(teamCategory);
        team = teamService.createTeam(crewCreateRequest);

        then(teamRepository).should().save(any());
        then(teamCategoryRepository).should().save(any());
    }
}