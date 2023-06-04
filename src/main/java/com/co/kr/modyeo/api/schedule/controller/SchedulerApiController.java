package com.co.kr.modyeo.api.schedule.controller;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerCreateRequest;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerUpdateRequest;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.service.SchedulerService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class SchedulerApiController {

    private final SchedulerService schedulerService;

    @PostMapping()
    public ResponseEntity<?> createScheduler(@RequestBody SchedulerCreateRequest schedulerCreateRequest,
                                             Principal principal){
        Long schedulerId = schedulerService.createScheduler(schedulerCreateRequest, Long.valueOf(principal.getName()));
        return ResponseHandler.generate()
                .data(schedulerId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping()
    public ResponseEntity<?> getSchedulers(SchedulerSearch schedulerSearch){
        Slice<SchedulerResponse> schedulers = schedulerService.getSchedulers(schedulerSearch);
        return ResponseHandler.generate()
                .data(schedulers)
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{schedulerId}")
    public ResponseEntity<?> getScheduler(@PathVariable Long schedulerId){
        SchedulerDetail scheduler = schedulerService.getScheduler(schedulerId);
        return ResponseHandler.generate()
                .data(scheduler)
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{schedulerId}")
    public ResponseEntity<?> deleteScheduler(@PathVariable Long schedulerId){
        schedulerService.deleteScheduler(schedulerId);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("")
    public ResponseEntity<?> updateScheduler(@RequestBody SchedulerUpdateRequest schedulerUpdateRequest){
        Long schedulerId = schedulerService.updateScheduler(schedulerUpdateRequest);
        return ResponseHandler.generate()
                .data(schedulerId)
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
