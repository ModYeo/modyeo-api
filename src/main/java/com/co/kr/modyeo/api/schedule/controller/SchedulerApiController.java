package com.co.kr.modyeo.api.schedule.controller;

import com.co.kr.modyeo.api.schedule.domain.dto.request.*;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
import com.co.kr.modyeo.api.schedule.service.SchedulerService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class SchedulerApiController {

    private final SchedulerService schedulerService;

    @PostMapping()
    public ResponseEntity<?> createScheduler(@RequestBody SchedulerCreateRequest schedulerCreateRequest,
                                             Principal principal) {
        Long schedulerId = schedulerService.createScheduler(schedulerCreateRequest, Long.valueOf(principal.getName()));
        return ResponseHandler.generate()
                .data(schedulerId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping()
    public ResponseEntity<?> getSchedulers(SchedulerSearch schedulerSearch) {
        Slice<SchedulerResponse> schedulers = schedulerService.getSchedulers(schedulerSearch);
        return ResponseHandler.generate()
                .data(schedulers)
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{schedulerId}")
    public ResponseEntity<?> getScheduler(@PathVariable Long schedulerId) {
        SchedulerDetail scheduler = schedulerService.getScheduler(schedulerId);
        return ResponseHandler.generate()
                .data(scheduler)
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{schedulerId}")
    public ResponseEntity<?> deleteScheduler(@PathVariable Long schedulerId,
                                             Principal principal) {
        schedulerService.deleteScheduler(schedulerId, Long.parseLong(principal.getName()));
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("")
    public ResponseEntity<?> updateScheduler(@RequestBody SchedulerUpdateRequest schedulerUpdateRequest,
                                             Principal principal) {
        Long schedulerId = schedulerService.updateScheduler(schedulerUpdateRequest, Long.parseLong(principal.getName()));
        return ResponseHandler.generate()
                .data(schedulerId)
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/status")
    public ResponseEntity<?> updateStatus(@RequestBody SchedulerStatusRequest schedulerStatusRequest,
                                          Principal principal) {
        Long schedulerId = schedulerService.updateStatus(schedulerStatusRequest, Long.parseLong(principal.getName()));
        return ResponseHandler.generate()
                .data(schedulerId)
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/member")
    public ResponseEntity<?> createMemberScheduler(@RequestBody MemberSchedulerCreateRequest memberSchedulerCreateRequest) {
        Long schedulerId = schedulerService.createMemberScheduler(memberSchedulerCreateRequest);
        return ResponseHandler.generate()
                .data(schedulerId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{schedulerId}")
    public ResponseEntity<?> getMemberSchedulers(@PathVariable Long schedulerId) {
        return null;
    }
}
