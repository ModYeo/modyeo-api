package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.dto.request.ApplicationFormRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamApplicationRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.ApplicationFormDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.MemberTeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.api.team.service.TeamApplicationService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-team")
@Api("팀 신청 api controller")
@RequiredArgsConstructor
public class TeamApplicationApiController {
    private final TeamApplicationService teamApplicationService;

    @ApiOperation(value = "팀 가입신청 폼 API")
    @PostMapping("/form")
    public ResponseEntity<?> createApplicationForm(@RequestBody ApplicationFormRequest applicationFormRequest) {
        teamApplicationService.createApplicationForm(applicationFormRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "팀 가입신청 폼 상세 조회 API")
    @GetMapping("/form/{teamId}")
    public ResponseEntity<?> getApplicationForm(@PathVariable(value = "teamId") Long teamId) {
        ApplicationFormDetail applicationFormDetail = teamApplicationService.getApplicationForm(teamId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(applicationFormDetail)
                .build());
    }

    @ApiOperation(value = "팀 가입신청폼 수정 API")
    @PatchMapping("/form/{applicationFormId}")
    public ResponseEntity<?> updateApplicationForm(
            @PathVariable(value = "applicationFormId") Long applicationFromId,
            @RequestBody ApplicationFormRequest applicationFormRequest) {
        teamApplicationService.updateApplicationForm(applicationFromId,applicationFormRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "팀 가입신청폼 삭제 API")
    @DeleteMapping("/form/{applicationFormId}")
    public ResponseEntity<?> deleteApplicationForm(
            @PathVariable(value = "applicationFormId") Long applicationFromId) {
        teamApplicationService.deleteApplicationForm(applicationFromId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "팀 가입신청 API")
    @PostMapping("")
    public ResponseEntity<?> applicantCrew(
            @RequestBody TeamApplicationRequest teamApplicationRequest
    ) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        teamApplicationRequest.setEmail(email);

        MemberTeam memberTeam = teamApplicationService.applicantCrew(teamApplicationRequest);

        if (memberTeam.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(JsonResultData.successResultBuilder()
                            .data(null)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JsonResultData.failResultBuilder()
                            .errorMessage(null)
                            .errorCode(null)
                            .build());
        }
    }

    @ApiOperation(value = "팀 가입신청 리스트 API")
    @GetMapping("")
    public ResponseEntity<?> getTeamApplication(
            @RequestParam(value = "teamId",name = "teamId",required = true)Long teamId){
        List<MemberTeamResponse> memberTeamResponseList = teamApplicationService.getTeamApplication(teamId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

    @ApiOperation(value = "팀 가입신청 변경 API")
    @PatchMapping("")
    public ResponseEntity<?> updateJoinState(
            @RequestParam(value = "memberTeamId", name = "memberTeamId", required = true) Long memberTeamId,
            @RequestParam(value = "joinStatus", name = "joinStatus", required = true) JoinStatus joinStatus
    ) {
        MemberTeam memberTeam = teamApplicationService.updateJoinStatus(memberTeamId, joinStatus);

        if (memberTeam.getId() != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(JsonResultData.successResultBuilder()
                            .data(null)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JsonResultData.failResultBuilder()
                            .errorMessage(null)
                            .errorCode(null)
                            .build());
        }
    }

    @ApiOperation(value = "팀 가입신청 취소")
    @DeleteMapping("/{memberTeamId}")
    public ResponseEntity<?> deleteTeamApplication(
            @PathVariable Long memberTeamId){
        teamApplicationService.deleteTeamApplication(memberTeamId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

}
