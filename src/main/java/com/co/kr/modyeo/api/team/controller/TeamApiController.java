package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamApiController {
    private final TeamService teamService;

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

    @GetMapping("")
    public ResponseEntity<?> getTeams(TeamSearch teamSearch){
        Slice<TeamResponse> teamResponses = teamService.getTeams(teamSearch);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(teamResponses)
                .build());
    }

    @GetMapping("/{team_id}")
    public ResponseEntity<?> getTeam(
            @PathVariable(value = "team_id") Long teamId){
        TeamDetail teamDetail = teamService.getTeam(teamId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(teamDetail)
                .build());
    }

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
