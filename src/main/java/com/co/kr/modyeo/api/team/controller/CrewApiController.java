package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.dto.request.CrewUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.service.CrewService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team/crew")
public class CrewApiController {

    private final CrewService crewService;

    @ApiOperation("크루원 정보 조회 API")
    @GetMapping("")
    public ResponseEntity<?> getCrew(
            @RequestParam(value = "teamId", name = "teamId", required = true) Long teamId) {
        List<CrewResponse> crewResponseList = crewService.getCrew(teamId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(crewResponseList)
                .build());
    }

    @ApiOperation("크루원 정보 LEVEL 변경 API")
    @PatchMapping("/level")
    public ResponseEntity<?> updateCrewLevel(CrewUpdateRequest crewUpdateRequest) {
        crewService.updateCrewLevel(crewUpdateRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation("크루원 강퇴 API")
    @DeleteMapping("/{crewId}")
    public ResponseEntity<?> deleteCrew(@PathVariable Long crewId) {
        crewService.deleteCrew(crewId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation("차단한 크루원 조회 API")
    @GetMapping("/deleted/{teamId}")
    public ResponseEntity<?> getInactiveCrew(@PathVariable Long teamId) {
        List<CrewResponse> crewResponseList = crewService.getInactiveCrew(teamId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(crewResponseList)
                .build());
    }

    @ApiOperation("차단 해제 API")
    @PatchMapping("/{crewId}/active")
    public ResponseEntity<?> updateCrewActive(@PathVariable Long crewId){
        crewService.updateCrewActive(crewId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }
}
