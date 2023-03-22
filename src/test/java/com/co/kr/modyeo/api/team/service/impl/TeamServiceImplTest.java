package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
import com.co.kr.modyeo.api.team.repository.TeamActivAreaRepository;
import com.co.kr.modyeo.api.team.repository.TeamCategoryRepository;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.api.team.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


class TeamServiceImplTest {
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamCategoryRepository teamCategoryRepository;

    @Mock
    private CrewRepository crewRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private EmdAreaRepository emdAreaRepository;

    @Mock
    private TeamActivAreaRepository teamActivAreaRepository;

    @Mock
    private CategoryRepository categoryRepository;

    Team FIXTURE_TEAM_01 = Team.of()
            .id(1L)
            .name("test1")
            .categoryList(new ArrayList<>())
            .crewList(new ArrayList<>())
            .build();

    Team FIXTURE_TEAM_02 = Team.of()
            .id(2L)
            .name("test2")
            .categoryList(new ArrayList<>())
            .crewList(new ArrayList<>())
            .build();

    Team FIXTURE_TEAM_03 = Team.of()
            .id(3L)
            .name("test3")
            .categoryList(new ArrayList<>())
            .crewList(new ArrayList<>())
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        teamService = new TeamServiceImpl(teamRepository, teamCategoryRepository, crewRepository, memberRepository, emdAreaRepository, teamActivAreaRepository, categoryRepository);
    }

    @Test
    @WithMockUser
    void getRecommendTeams() {
        List<Team> teamList = new ArrayList<>();
        teamList.add(FIXTURE_TEAM_01);
        teamList.add(FIXTURE_TEAM_02);
        teamList.add(FIXTURE_TEAM_03);

        List<Team> myTeam = new ArrayList<>();
        myTeam.add(FIXTURE_TEAM_01);

        given(teamRepository.getRecommendTeams(any(),any())).willReturn(teamList);
        given(teamRepository.findMyTeam(any())).willReturn(myTeam);

        List<TeamResponse> recommendTeams = teamService.getRecommendTeams(1L, new ArrayList<>(), 1L);

        assertThat(recommendTeams.size()).isEqualTo(2);
        assertThat(recommendTeams.get(1).getId()).isEqualTo(FIXTURE_TEAM_03.getId());
        assertThat(recommendTeams.get(1).getName()).isEqualTo(FIXTURE_TEAM_03.getName());
        assertThat(recommendTeams.get(0).getId()).isEqualTo(FIXTURE_TEAM_02.getId());
        assertThat(recommendTeams.get(0).getName()).isEqualTo(FIXTURE_TEAM_02.getName());
    }
}