package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.team.domain.entity.Team;
import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.team.repository.TeamRepository;
import com.co.kr.modyeo.team.repository.MemberTeamRepository;
import com.co.kr.modyeo.member.repository.MemberRepository;
import com.co.kr.modyeo.team.service.TeamApplicationService;
import com.co.kr.modyeo.team.service.impl.TeamApplicationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        teamApplicationService = new TeamApplicationServiceImpl(memberRepository, teamRepository, memberTeamRepository);
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
                .crew(team)
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

        Assertions.assertThat(memberTeam.getJoinStatus()).isEqualTo(JoinStatus.APPLICATION);
    }
}