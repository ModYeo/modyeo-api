package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.api.team.service.TeamApplicationService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member-team")
@RequiredArgsConstructor
public class TeamApplicationApiController {
    private final TeamApplicationService teamApplicationService;

    @PostMapping("")
    public ResponseEntity<?> applicantCrew(
            @RequestParam(value = "memberId",name = "memberId",required = true)Long memberId,
            @RequestParam(value = "teamId",name = "teamId",required = true)Long teamId
    ){
        MemberTeam memberTeam = teamApplicationService.applicantCrew(memberId,teamId);

        if (memberTeam.getId() != null){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(JsonResultData.successResultBuilder()
                            .data(null)
                            .build());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JsonResultData.failResultBuilder()
                            .errorMessage(null)
                            .errorCode(null)
                            .build());
        }
    }

    @PatchMapping("")
    public ResponseEntity<?> updateJoinState(
            @RequestParam(value = "memberTeamId",name = "memberTeamId",required = true) Long memberTeamId,
            @RequestParam(value = "joinStatus",name = "joinStatus",required = true) JoinStatus joinStatus
            ){
        MemberTeam memberTeam = teamApplicationService.updateJoinStatus(memberTeamId,joinStatus);

        if (memberTeam.getId() != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(JsonResultData.successResultBuilder()
                            .data(null)
                            .build());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JsonResultData.failResultBuilder()
                            .errorMessage(null)
                            .errorCode(null)
                            .build());
        }
    }

}
