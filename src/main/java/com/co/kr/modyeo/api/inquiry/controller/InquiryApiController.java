package com.co.kr.modyeo.api.inquiry.controller;

import com.co.kr.modyeo.api.inquiry.domain.dto.request.*;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.AnswerDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.service.InquiryService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api("문의사항 API Controller")
@RequiredArgsConstructor
public class InquiryApiController {
    private final InquiryService inquiryService;

    @GetMapping(value="/inquiry")
    @ApiOperation(value="문의사항 Index 페이지 API(어드민)")
    public ResponseEntity<?> getInquiryIndexPage(InquirySearch inquirySearch){
        Slice<InquiryResponse> inquiryResponseSlice = inquiryService.getInquiryIndexPage(inquirySearch);

        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(inquiryResponseSlice)
                .build();
    }

    @GetMapping(value = "/inquiry/list")
    @ApiOperation(value = "문의사항 조회 API(어드민)")
    public ResponseEntity<?> getInquiries(InquirySearch inquirySearch) {
        Slice<InquiryResponse> inquiryResponseSlice = inquiryService.getSelectedInquiries(inquirySearch);

        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(inquiryResponseSlice)
                .build();
    }

    //문의사항 조회
    @GetMapping(value = "/inquiry/{inquiry_id}")
    @ApiOperation(value = "문의사항 상세 조회(어드민)")
    public ResponseEntity<?> getInquiryDetail(@PathVariable(name = "inquiry_id") Long id) {
        InquiryDetail inquiryDetail = inquiryService.getInquiryDetail(id);

        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(inquiryDetail)
                .build();
    }

    //문의사항 작성
    @PostMapping(value = "/inquiry")
    @ApiOperation(value = "문의사항 작성 API")
    public ResponseEntity<?> createInquiry(@RequestBody @Valid InquiryCreateRequest InquiryCreateRequest) {
        Long inquiryId = inquiryService.createInquiry(InquiryCreateRequest);

        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(inquiryId)
                .build();
    }

    //문의사항 수정
    @ApiOperation(value = "문의사항 수정 API")
    @PatchMapping(value = "/inquiry")
    public ResponseEntity<?> updateInquiry(@RequestBody @Valid InquiryUpdateRequest InquiryUpdateRequest) {
        Long inquiryId = inquiryService.updateInquiry(InquiryUpdateRequest).getId();

        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(inquiryId)
                .build();
    }

    //문의사항 삭제
    @ApiOperation(value = "문의사항 삭제 API")
    @DeleteMapping(value = "/inquiry/{inquiry_id}")
    public ResponseEntity<?> deleteInquiry(@PathVariable(name = "inquiry_id") Long id) {
        inquiryService.deleteInquiry(id);

        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }

    @ApiOperation(value = "답변 조회 API")
    @GetMapping(value = "/answer/{answer_id}")
    public ResponseEntity<?> getAnswer(@PathVariable(name = "answer_id") Long id){
        AnswerDetail answerDetail = inquiryService.getAnswer(id);

        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(answerDetail)
                .build();
    }

    @ApiOperation(value = "답변 생성 API(어드민)")
    @PostMapping(value = "/answer")
    public ResponseEntity<?> createAnswer(@RequestBody @Valid AnswerCreateRequest answerCreateRequest) {
        //Answer answer = inquiryService.createAnswer(answerRequest);
        Long answerId = inquiryService.createAnswer(answerCreateRequest);

        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(answerId)
                .build();
    }

    @ApiOperation(value = "답변 수정 API(어드민)")
    @PatchMapping(value = "/answer")
    public ResponseEntity<?> updateAnswer(@RequestBody @Valid AnswerUpdateRequest answerUpdateRequest) {
        //Answer answer = inquiryService.updateAnswer(answerRequest);
        Long answerId = inquiryService.updateAnswer(answerUpdateRequest);

        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(answerId)
                .build();
    }

    @ApiOperation(value = "답변 삭제 API(어드민)")
    @DeleteMapping(value = "/answer/{answer_id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable(name = "answer_id") Long id) {
        inquiryService.deleteAnswer(id);

        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }
}
