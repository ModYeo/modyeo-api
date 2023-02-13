package com.co.kr.modyeo.api.notice.controller;

import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeUpdateRequest;
import com.co.kr.modyeo.api.notice.domain.dto.request.NoticeCreateRequest;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeDetail;
import com.co.kr.modyeo.api.notice.domain.dto.response.NoticeResponse;
import com.co.kr.modyeo.api.notice.service.NoticeService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeApiController {

    private final NoticeService noticeService;

    @ApiOperation(value = "알림 목록 조회 API(어드민)")
    @GetMapping("")
    public ResponseEntity<?> getNotices(){
        List<NoticeResponse> noticeResponseList = noticeService.getNotices();
        return ResponseHandler.generate()
                .data(noticeResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "알림 생성 API(어드민)")
    @PostMapping("")
    public ResponseEntity<?> createNotice(@Valid @RequestBody NoticeCreateRequest noticeCreateRequest){
        Long noticeId = noticeService.createNotice(noticeCreateRequest);
        return ResponseHandler.generate()
                .data(noticeId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value = "알림 상세 조회 API(어드민)")
    @GetMapping("/{id}")
    public ResponseEntity<?> getNotice(@PathVariable Long id){
        NoticeDetail noticeDetail = noticeService.getNotice(id);
        return ResponseHandler.generate()
                .data(noticeDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "알림 수정 API(어드민)")
    @PatchMapping("")
    public ResponseEntity<?> updateNotice(@Valid @RequestBody NoticeUpdateRequest noticeUpdateRequest){
        Long noticeId = noticeService.updateNotice(noticeUpdateRequest);
        return ResponseHandler.generate()
                .data(noticeId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "알림 삭제 API(어드민)")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable Long id){
        noticeService.deleteNotice(id);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
