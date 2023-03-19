package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.api.team.domain.dto.request.ApplicationFormRequest;
import com.co.kr.modyeo.api.team.domain.entity.ApplicationForm;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.repository.ApplicationFormRepository;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.api.team.repository.MemberTeamRepository;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.team.service.TeamApplicationService;
import com.co.kr.modyeo.api.team.service.impl.TeamApplicationServiceImpl;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.BoardErrorCode;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TeamApplicationServiceImplTest {

    private TeamApplicationService teamApplicationService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private MemberTeamRepository memberTeamRepository;

    @Mock
    private CrewRepository crewRepository;

    @Mock
    private ApplicationFormRepository applicationFormRepository;

    Member FIXTURE_MEM_01 = Member.of()
            .id(1L)
            .email("qws458@naver.com")
            .build();

    Team FIXTURE_TEAM_01 = Team.of()
            .id(1L)
            .name("test crew")
            .build();

    MemberTeam FIXTURE_MEM_TEAM_01 = MemberTeam.joinApplicationBuilder()
            .team(FIXTURE_TEAM_01)
            .member(FIXTURE_MEM_01)
            .build();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        teamApplicationService = new TeamApplicationServiceImpl(memberRepository, teamRepository, memberTeamRepository, crewRepository, applicationFormRepository);
    }

    @Test
    @DisplayName("크루 가입 승인1")
    void joinCrew() {
        Member member = Member.of()
                .id(1L)
                .email("qws458@naver.com")
                .build();

        Team team = Team.of()
                .id(1L)
                .name("test crew")
                .build();

        MemberTeam memberTeam = MemberTeam.joinApplicationBuilder()
                .team(team)
                .member(member)
                .build();


        given(memberRepository.findById(any())).willReturn(Optional.of(member));
        given(teamRepository.findById(any())).willReturn(Optional.of(team));
        given(memberTeamRepository.save(any())).willReturn(memberTeam);

        member = memberRepository.findById(1L).get();
        team = teamRepository.findById(1L).get();

        memberTeam = memberTeamRepository.save(memberTeam);

        then(memberRepository).should().findById(any());
        then(teamRepository).should().findById(any());
        then(memberTeamRepository).should().save(any());

        assertThat(memberTeam.getJoinStatus()).isEqualTo(JoinStatus.APPLICATION);
    }

    @Test
    void updateApplicationFormSuccess() {
        ApplicationFormRequest applicationFormRequest = new ApplicationFormRequest();
        applicationFormRequest.setContent("test111");

        Team team = Team.of()
                .id(1L)
                .name("test crew")
                .build();

        ApplicationForm applicationForm = ApplicationForm.of()
                .id(1L)
                .team(team)
                .content("test")
                .dutyNote("test")
                .birthdayAgree(Yn.Y)
                .sexAgree(Yn.Y)
                .geoAgree(Yn.Y)
                .build();

        given(applicationFormRepository.findById(any())).willReturn(Optional.of(applicationForm));

        teamApplicationService.updateApplicationForm(1L, applicationFormRequest);

        then(applicationFormRepository).should().findById(any());

        assertThat(applicationForm.getContent()).isEqualTo(applicationFormRequest.getContent());
    }

    @Test
    void deleteTeamApplication(){
        given(memberTeamRepository.findById(any())).willReturn(Optional.of(FIXTURE_MEM_TEAM_01));

        teamApplicationService.deleteTeamApplication(FIXTURE_MEM_TEAM_01.getId());

        then(memberTeamRepository).should().findById(any());
        then(memberTeamRepository).should().delete(any());
    }

    @Test
    void deleteTeamApplicationFail(){
        given(memberTeamRepository.findById(any())).willThrow(ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(TeamErrorCode.NOT_FOUND_APPLICANT.getMessage())
                .errorCode(TeamErrorCode.NOT_FOUND_APPLICANT.getCode())
                .build());

        assertThatThrownBy(() -> {
            teamApplicationService.deleteTeamApplication(any());
        }).isInstanceOf(Exception.class)
                .hasMessageContaining(TeamErrorCode.NOT_FOUND_APPLICANT.getMessage());
    }
}