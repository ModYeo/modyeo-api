package com.co.kr.modyeo.api.schedule.controller;

import com.co.kr.modyeo.api.schedule.service.SchedulerService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class SchedulerApiController {

    private final SchedulerService schedulerService;

    @PostMapping()
    public ResponseEntity<?> createScheduler(){
        return ResponseHandler.generate()
                .build();
    }
}
