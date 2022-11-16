package com.co.kr.modyeo.api.team.controller;

import com.co.kr.modyeo.api.team.domain.dto.request.CrewUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.SearchCrew;
import com.co.kr.modyeo.api.team.service.CrewService;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            SearchCrew searchCrew) {
        List<CrewResponse> crewResponseList = crewService.getCrew(searchCrew);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(crewResponseList)
                .build();
    }

    @ApiOperation("크루원 정보 LEVEL 변경 API")
    @PatchMapping("/level")
    public ResponseEntity<?> updateCrewLevel(CrewUpdateRequest crewUpdateRequest) {
        Long crewId = crewService.updateCrewLevel(crewUpdateRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(crewId)
                .build();
    }

    @ApiOperation("크루원 강퇴 API")
    @DeleteMapping("/{crewId}")
    public ResponseEntity<?> deleteCrew(@PathVariable Long crewId) {
        crewService.deleteCrew(crewId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }

    @ApiOperation("차단 해제 API")
    @PatchMapping("/{crewId}/active")
    public ResponseEntity<?> updateCrewActive(@PathVariable Long crewId){
        crewId = crewService.updateCrewActive(crewId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(crewId)
                .build();
    }

    @ApiOperation("크루 탈퇴 API")
    @PatchMapping("/{teamId}/inactive")
    public ResponseEntity<?> updateCrewInActive(@PathVariable Long teamId){
        Long crewId = crewService.updateCrewInActive(teamId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(crewId)
                .build();
    }
}
