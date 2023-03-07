package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateActivAreaRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateLimitMeterRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.SomeoneTeamSearch;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.service.TeamAreaService;
import com.co.kr.modyeo.api.team.service.TeamService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/team")
@Api("팀 api controller")
@RequiredArgsConstructor
public class TeamApiController {
    private final TeamService teamService;
    private final TeamAreaService teamAreaService;

    @ApiOperation(value = "팀 생성 API")
    @PostMapping("")
    public ResponseEntity<?> createTeam(@Valid @RequestBody TeamCreateRequest teamCreateRequest){
        Long teamId = teamService.createTeam(teamCreateRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(teamId)
                .build();
    }

    @ApiOperation(value = "")
    @GetMapping("/recommend")
    public ResponseEntity<?> getRecommendTeams(@RequestParam(value = "emdId", name = "emdId", required = true) Long emdId,
                                               @RequestParam(value = "categoryIdList", name = "categoryIdList", required = true)List<Long> categoryIdList){
        List<TeamResponse> teamResponses = teamService.getRecommendTeams(emdId,categoryIdList);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(teamResponses)
                .build();
    }

    @ApiOperation(value = "팀 슬라이스 조회 API")
    @GetMapping("")
    public ResponseEntity<?> getTeams(TeamSearch teamSearch){
        Slice<TeamResponse> teamResponses = teamService.getTeams(teamSearch);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(teamResponses)
                .build();
    }

    @ApiOperation(value="특정 멤버의 팀 슬라이스 조회 API")
    @GetMapping("/member")
    public ResponseEntity<?> getSomeonesTeams(SomeoneTeamSearch someoneTeamSearch){
        Slice<TeamResponse> memberTeam = teamService.getSomeoneTeam(someoneTeamSearch);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(memberTeam)
                .build();
    }

    @ApiOperation(value = "팀 상세 조회 API")
    @GetMapping("/{team_id}")
    public ResponseEntity<?> getTeam(
            @PathVariable(value = "team_id") Long teamId){
        TeamDetail teamDetail = teamService.getTeam(teamId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(teamDetail)
                .build();
    }

    @ApiOperation(value = "팀 수정 API")
    @PatchMapping("")
    public ResponseEntity<?> updateTeam(@Valid @RequestBody TeamUpdateRequest teamRequest){
        Long teamId = teamService.updateTeam(teamRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(teamId)
                .build();
    }

    @ApiOperation(value = "팀 삭제 API")
    @DeleteMapping("/{team_id}")
    public ResponseEntity<?> deleteTeam(
            @PathVariable("team_id")Long teamId
    ){
        teamService.deleteTeam(teamId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }

    @ApiOperation(value="팀 활동지역 생성 API")
    @PostMapping("/activeArea")
    public ResponseEntity<?> createTeamActiveArea(
            @Valid @RequestBody TeamCreateActivAreaRequest teamCreateActivAreaRequest)
    {
        Long teamId = teamAreaService.createTeamActivArea(teamCreateActivAreaRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(teamId)
                .build();
    }

    @ApiOperation(value="팀 활동반경 수정 API")
    @PatchMapping("/activeArea")
    public ResponseEntity<?> updateTeamLimitMeter(@RequestBody TeamUpdateLimitMeterRequest teamUpdateLimitMeterRequest){
        Long teamId = teamAreaService.updateTeamActivArea(teamUpdateLimitMeterRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(teamId)
                .build();
    }

    @ApiOperation(value="팀 활동지역 삭제 API")
    @DeleteMapping({"/activeArea/{team_active_area_id}"})
    public ResponseEntity<?> deleteTeamArea(
            @PathVariable("team_active_area_id") Long teamActiveAreaId){
        teamAreaService.deleteTeamActivArea(teamActiveAreaId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }
}
