package com.co.kr.modyeo.api.notice.controller;

import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeCreateRequest;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeDetail;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeResponse;
import com.co.kr.modyeo.api.notice.service.NoticeService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeApiController {

    private final NoticeService noticeService;

    @GetMapping("")
    public ResponseEntity<?> getNotices(){
        List<NoticeResponse> noticeResponseList = noticeService.getNotices();
        return ResponseHandler.generate()
                .data(noticeResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("")
    public ResponseEntity<?> createNotice(@RequestBody NoticeCreateRequest noticeCreateRequest){
        Long noticeId = noticeService.createNotice(noticeCreateRequest);
        return ResponseHandler.generate()
                .data(noticeId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotice(@PathVariable Long id){
        NoticeDetail noticeDetail = noticeService.getNotice(id);
        return ResponseHandler.generate()
                .data(noticeDetail)
                .status(HttpStatus.OK)
                .build();
    }
}
