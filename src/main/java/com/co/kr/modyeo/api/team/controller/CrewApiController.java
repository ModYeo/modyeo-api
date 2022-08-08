package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.service.CrewService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
