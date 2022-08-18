package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.dto.request.CrewUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.service.CrewService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team/crew")
public class CrewApiController {

    private final CrewService crewService;

    @GetMapping("")
    public ResponseEntity<?> getCrew(
            @RequestParam(value = "teamId", name = "teamId", required = true) Long teamId) {
        List<CrewResponse> crewResponseList = crewService.getCrew(teamId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(crewResponseList)
                .build());
    }

    @PatchMapping("/level")
    public ResponseEntity<?> updateCrewLevel(CrewUpdateRequest crewUpdateRequest){
        crewService.updateCrewLevel(crewUpdateRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }
}