package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import com.co.kr.modyeo.member.domain.entity.Team;
import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.member.domain.enumerate.JoinStatus;
import com.co.kr.modyeo.member.repository.TeamRepository;
import com.co.kr.modyeo.member.repository.MemberTeamRepository;
import com.co.kr.modyeo.member.repository.MemberRepository;
import com.co.kr.modyeo.member.service.TeamApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamApplicationServiceImpl implements TeamApplicationService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final MemberTeamRepository memberTeamRepository;

    @Override
    public MemberTeam applicantCrew(Long memberId, Long crewId) {
        Member member = findMember(memberId);
        Team team = findTeam(crewId);
        MemberTeam memberTeam = memberTeamRepository.findByTeamAndMember(team,member);
        if (memberTeam != null){
            if (memberTeam.getJoinStatus() != JoinStatus.APPROVAL){
                throw ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("ALREADY_JOINED_CREW")
                        .errorMessage("이미 가입된 크루입니다.")
                        .build();
            }
        }


        MemberTeam newMemberTeam = MemberTeam.joinApplicationBuilder()
                .team(team)
                .member(member)
                .build();

        return memberTeamRepository.save(newMemberTeam);
    }

    @Override
    public MemberTeam updateJoinStatus(Long memberCrewId, JoinStatus joinStatus) {
        MemberTeam memberTeam = memberTeamRepository.findById(memberCrewId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_OBJECT")
                        .errorMessage("찾을 수 없는 OBJECT 입니다.")
                        .build());

        memberTeam.changeJoinStatus(joinStatus);
        return memberTeam;
    }

    private Member findMember(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .build());
    }

    private Team findTeam(Long teamId){
        return teamRepository.findById(teamId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                        .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                        .build());
    }

}
