package com.co.kr.modyeo.api.inquiry.controller;

import com.co.kr.modyeo.api.inquiry.domain.dto.Request.AnswerRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.Request.InquiryRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.service.impl.InquiryServiceImpl;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//어노테이션 각 의미 찾아보기
@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api")
@Api("문의사항 API Controller")
public class InquiryApiController {
    /*
    문의사항 조회
    1.최초 화면으로 이동 시
        - 자주 묻는 질문(DB로 관리하기 / 일반 문의와 통합관리)
        - 문의작성 버튼
        - 내 문의 답변 확인하기
     */
    private InquiryServiceImpl inquiryService;

    @GetMapping(value = "/inquiry")
    @ApiOperation(value = "문의사항 리스트 조회 API")
    public ResponseEntity<?> getInquiries(@RequestBody InquiryRequest inquiryRequest) {
        List<InquiryResponse> inquiryResponseList = inquiryService.getInquiries(inquiryRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(inquiryResponseList)
                .build());
    }

    //문의사항 조회
    @ApiOperation(value = "문의사항 상세 조회")
    @GetMapping(value = "/inquiry/{inquiry_id}")
    public ResponseEntity<?> getInquiryDetail(@PathVariable(name = "inquiry_id") Long id) {
        InquiryDetail inquiryDetail = inquiryService.getInquiryDetail(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonResultData.successResultBuilder()
                        .data(inquiryDetail)
                        .build());
    }

    //문의사항 작성
    @PostMapping(value = "/inquiry")
    @ApiOperation(value = "문의사항 작성 API")
    public ResponseEntity<?> createInquiry(@RequestBody InquiryRequest inquiryRequest) { //TODO : 제네릭 '?'에 대해 공부하기.
        inquiryService.createInquiry(inquiryRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

    //문의사항 수정
    @ApiOperation(value = "문의사항 수정 API")
    @PatchMapping(value = "/inquiry")
    public ResponseEntity<?> updateInquiry(@RequestBody InquiryRequest inquiryRequest) {
        //inquiryService.
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

    //문의사항 삭제
    @ApiOperation(value = "문의사항 삭제 API")
    @DeleteMapping(value = "/inquiry/{inquiry_id}")
    public ResponseEntity<?> deleteInquiry(@PathVariable(name = "id") Long id) {
        inquiryService.deleteInquiry(id);

        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "답변 생성 API")
    @PostMapping(value = "/answer")
    public ResponseEntity<?> createAnswer(@RequestBody AnswerRequest answerRequest) {
        Answer answer = inquiryService.craeteAnswer(answerRequest);

        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build()
        );
    }

    @ApiOperation(value = "답변 수정 API")
    @PatchMapping(value = "/answer")
    public ResponseEntity<?> updateAnswer(@RequestBody AnswerRequest answerRequest) {
        Answer answer = inquiryService.updateAnswer(answerRequest);

        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build()
        );
    }

    @ApiOperation(value = "답변 삭제 API")
    @DeleteMapping(value = "/answer/{answer_id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable(name = "answer_id") Long id) {
        inquiryService.deleteAnswer(id);

        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build()
        );
    }
}
