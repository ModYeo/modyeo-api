package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.dto.request.ApplicationFormRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamApplicationRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.ApplicationFormDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.MemberTeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.ApplicationForm;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.api.team.repository.ApplicationFormRepository;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
import com.co.kr.modyeo.api.team.repository.MemberTeamRepository;
import com.co.kr.modyeo.api.team.service.TeamApplicationService;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.util.SecurityUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamApplicationServiceImpl implements TeamApplicationService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final MemberTeamRepository memberTeamRepository;
    private final CrewRepository crewRepository;
    private final ApplicationFormRepository applicationFormRepository;

    @Override
    @Transactional
    public Long applicantCrew(TeamApplicationRequest teamApplicationRequest) {
        Member member = findMember(teamApplicationRequest.getEmail());
        Team team = findTeam(teamApplicationRequest.getTeamId());
        MemberTeam memberTeam = memberTeamRepository.findByTeamAndMember(team, member);
        if (memberTeam != null) {
            if (memberTeam.getJoinStatus() != JoinStatus.APPROVAL) {
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
                .introduce(teamApplicationRequest.getIntroduce())
                .build();

        return memberTeamRepository.save(newMemberTeam).getId();
    }

    @Override
    @Transactional
    public Long updateJoinStatus(Long memberCrewId, JoinStatus joinStatus) {
        MemberTeam memberTeam = memberTeamRepository.findMemberTeamById(memberCrewId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(TeamErrorCode.NOT_FOUND_APPLICANT.getCode())
                        .errorMessage(TeamErrorCode.NOT_FOUND_APPLICANT.getMessage())
                        .build());

        if (JoinStatus.DENIAL.equals(joinStatus)) {
            memberTeam.changeDenial();
        } else {
            Team team = memberTeam.getTeam();
            Member member = memberTeam.getMember();

            Crew crew = Crew.createCrewBuilder()
                    .member(member)
                    .team(team)
                    .build();

            crewRepository.save(crew);
            memberTeamRepository.delete(memberTeam);
        }

        return memberTeam.getId();
    }

    @Override
    @Transactional
    public Long createApplicationForm(ApplicationFormRequest applicationFormRequest) {
        Team team = teamRepository.findById(applicationFormRequest.getTeamId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                        .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                        .build());

        String email = SecurityUtil.getCurrentEmail();

        CrewLevel crewLevel = crewRepository.findCrewLevelByTeamIdAndEmail(team.getId(), email);

        if (crewLevel != null) {
            if (CrewLevel.EXIT.equals(crewLevel)) {
                throw ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(TeamErrorCode.EXIT_TEAM.getMessage())
                        .errorCode(TeamErrorCode.EXIT_TEAM.getCode())
                        .build();
            } else if (!CrewLevel.LEAVE.equals(crewLevel)) {
                throw ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(TeamErrorCode.ALREADY_JOINED_TEAM.getMessage())
                        .errorCode(TeamErrorCode.ALREADY_JOINED_TEAM.getCode())
                        .build();
            }
        }

        ApplicationForm applicationForm = ApplicationFormRequest.toEntity(applicationFormRequest, team);
        return applicationFormRepository.save(applicationForm).getId();
    }

    @Override
    public ApplicationFormDetail getApplicationForm(Long teamId) {
        ApplicationForm applicationForm = applicationFormRepository.findApplicationFormByTeamId(teamId);
        return ApplicationFormDetail.toDto(applicationForm);
    }

    @Override
    @Transactional
    public Long updateApplicationForm(Long applicationFromId, ApplicationFormRequest applicationFormRequest) {
        ApplicationForm applicationForm = applicationFormRepository.findById(applicationFromId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(TeamErrorCode.NOT_FOUND_APPLICATION_FORM.getCode())
                        .errorMessage(TeamErrorCode.NOT_FOUND_APPLICATION_FORM.getMessage())
                        .build());

        ApplicationForm.updateBuilder()
                .applicationForm(applicationForm)
                .content(applicationFormRequest.getContent())
                .dutyNote(applicationFormRequest.getDutyNote())
                .birthdayAgree(applicationFormRequest.getBirthdayAgree())
                .geoAgree(applicationFormRequest.getGeoAgree())
                .sexAgree(applicationFormRequest.getSexAgree())
                .build();

        return applicationForm.getId();
    }

    @Override
    @Transactional
    public void deleteApplicationForm(Long applicationFromId) {
        ApplicationForm applicationForm = applicationFormRepository.findById(applicationFromId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(TeamErrorCode.NOT_FOUND_APPLICATION_FORM.getCode())
                        .errorMessage(TeamErrorCode.NOT_FOUND_APPLICATION_FORM.getMessage())
                        .build());

        applicationFormRepository.delete(applicationForm);
    }

    @Override
    public List<MemberTeamResponse> getTeamApplication(Long teamId) {
        return memberTeamRepository.findByTeamId(teamId).stream()
                .map(MemberTeamResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTeamApplication(Long memberTeamId) {
        MemberTeam memberTeam = memberTeamRepository.findById(memberTeamId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(TeamErrorCode.NOT_FOUND_APPLICANT.getMessage())
                        .errorCode(TeamErrorCode.NOT_FOUND_APPLICANT.getCode())
                        .build());

        memberTeamRepository.delete(memberTeam);
    }

    private Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .build());
    }

    private Team findTeam(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                        .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                        .build());
    }

}
