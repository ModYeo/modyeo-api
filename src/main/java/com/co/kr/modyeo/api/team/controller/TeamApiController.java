package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.service.TeamService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
@Api("팀 api controller")
@RequiredArgsConstructor
public class TeamApiController {
    private final TeamService teamService;

    @ApiOperation(value = "팀 생성 API")
    @PostMapping("")
    public ResponseEntity<?> createTeam(@RequestBody TeamRequest teamRequest){
        Team team = teamService.createTeam(teamRequest);
        if (team != null){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(JsonResultData.successResultBuilder()
                            .data(null)
                            .build());
        }else{
            return ResponseEntity
                    .badRequest()
                    .body(JsonResultData.failResultBuilder()
                            .build());
        }
    }

    @ApiOperation(value = "팀 슬라이스 조회 API")
    @GetMapping("")
    public ResponseEntity<?> getTeams(TeamSearch teamSearch){
        Slice<TeamResponse> teamResponses = teamService.getTeams(teamSearch);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(teamResponses)
                .build());
    }

    @ApiOperation(value = "팀 상세 조회 API")
    @GetMapping("/{team_id}")
    public ResponseEntity<?> getTeam(
            @PathVariable(value = "team_id") Long teamId){
        TeamDetail teamDetail = teamService.getTeam(teamId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(teamDetail)
                .build());
    }

    @ApiOperation(value = "팀 수정 API")
    @PatchMapping("")
    public ResponseEntity<?> updateTeam(@RequestBody TeamRequest teamRequest){
        Team team = teamService.updateTeam(teamRequest);

        if (team.getId() != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(JsonResultData.successResultBuilder()
                            .data(null)
                            .build());
        }else{
            return ResponseEntity
                    .badRequest()
                    .body(JsonResultData.failResultBuilder()
                            .build());
        }
    }

    @ApiOperation(value = "팀 삭제 API")
    @DeleteMapping("/{team_id}")
    public ResponseEntity<?> deleteTeam(
            @PathVariable("team_id")Long teamId
    ){
        teamService.deleteTeam(teamId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

}
