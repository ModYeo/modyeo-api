package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.member.domain.dto.request.CrewRequest;
import com.co.kr.modyeo.member.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/crew")
@RequiredArgsConstructor
public class CrewApiController {
    private final CrewService crewService;

    @PostMapping("")
    public ResponseEntity<?> createCrew(@RequestBody CrewRequest crewRequest){
        Crew crew = crewService.createCrew(crewRequest);
        if (crew != null){
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
    public ResponseEntity<?> getCrew(CrewSearch crewSearch){
        Slice<CrewResponse> crewResponseList = crewService.getCrew(crewSearch);
        return ResponseEntity.ok(crewResponseList);
    }

    @PatchMapping("")
    public ResponseEntity<?> updateCrew(@RequestBody CrewRequest crewRequest){
        Crew crew = crewService.updateCrew(crewRequest);

        if (crew.getId() != null){
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

    @DeleteMapping("/{crew_id}")
    public ResponseEntity<?> deleteCrew(
            @PathVariable("crew_id")Long crewId
    ){
        crewService.deleteCrew(crewId);
    }

}
