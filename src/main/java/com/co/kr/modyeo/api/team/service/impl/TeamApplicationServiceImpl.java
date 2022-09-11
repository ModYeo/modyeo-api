package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
import com.co.kr.modyeo.api.team.repository.MemberTeamRepository;
import com.co.kr.modyeo.api.team.service.TeamApplicationService;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamApplicationServiceImpl implements TeamApplicationService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final MemberTeamRepository memberTeamRepository;
    private final CrewRepository crewRepository;

    @Override
    @Transactional
    public MemberTeam applicantCrew(String email, Long crewId) {
        Member member = findMember(email);
        Team team = findTeam(crewId);
        MemberTeam memberTeam = memberTeamRepository.findByTeamAndMember(team,member);
        if (memberTeam != null){
            if (memberTeam.getJoinStatus() != JoinStatus.APPROVAL){
                throw ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(TeamErrorCode.ALREADY_JOINED_TEAM.getCode())
                        .errorMessage(TeamErrorCode.ALREADY_JOINED_TEAM.getMessage())
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
    @Transactional
    public MemberTeam updateJoinStatus(Long memberCrewId, JoinStatus joinStatus) {
        MemberTeam memberTeam = memberTeamRepository.findMemberTeamById(memberCrewId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(TeamErrorCode.NOT_FOUND_APPLICANT.getCode())
                        .errorMessage(TeamErrorCode.NOT_FOUND_APPLICANT.getMessage())
                        .build());

        if (JoinStatus.DENIAL.equals(joinStatus)){
            memberTeam.changeDenial();
        }else{
            Team team = memberTeam.getTeam();
            Member member = memberTeam.getMember();

            Crew crew = Crew.createCrewBuilder()
                    .member(member)
                    .team(team)
                    .build();

            crewRepository.save(crew);
            memberTeamRepository.delete(memberTeam);
        }

        return memberTeam;
    }

    private Member findMember(String email){
        return memberRepository.findByEmail(email)
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
