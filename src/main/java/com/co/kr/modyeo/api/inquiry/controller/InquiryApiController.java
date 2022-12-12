package com.co.kr.modyeo.api.inquiry.controller;

import com.co.kr.modyeo.api.inquiry.domain.dto.request.AnswerRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.request.InquiryRequest;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.AnswerDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryDetail;
import com.co.kr.modyeo.api.inquiry.domain.dto.Response.InquiryResponse;
import com.co.kr.modyeo.api.inquiry.domain.dto.search.InquirySearch;
import com.co.kr.modyeo.api.inquiry.service.InquiryService;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.co.kr.modyeo.common.util.SecurityUtil.checkAuthority;

@RestController
@RequestMapping("/api")
@Api("문의사항 API Controller")
@RequiredArgsConstructor
public class InquiryApiController {
    private final InquiryService inquiryService;

    @GetMapping(value="/inquiry")
    @ApiOperation(value="문의사항 Index 페이지 API")
    public ResponseEntity<?> getInquiryIndexPage(InquirySearch inquirySearch){
        Slice<InquiryResponse> inquiryResponseSlice = inquiryService.getInquiryIndexPage(inquirySearch);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(inquiryResponseSlice)
                .build());
    }

    @GetMapping(value="/inquiry/my")
    @ApiOperation(value="나의 문의사항 조회(일반유저) API")
    public ResponseEntity<?> getMyInquiries(InquirySearch inquirySearch){
        Authority auth = checkAuthority();
        if (auth!=Authority.ROLE_USER) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            Slice<InquiryResponse> inquiryResponseSlice = inquiryService.getMyInquiries(inquirySearch);
            return ResponseEntity.ok(JsonResultData.successResultBuilder()
                    .data(inquiryResponseSlice)
                    .build());
        }
    }

    @GetMapping(value = "/inquiry/admin")
    @ApiOperation(value = "전체 문의사항 조회(관리자) API")
    public ResponseEntity<?> getInquiries(InquirySearch inquirySearch) {
        Authority auth = checkAuthority();
        if (auth!=Authority.ROLE_ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } else {
            Slice<InquiryResponse> inquiryResponseSlice = inquiryService.getAllInquiries(inquirySearch, auth);
            return ResponseEntity.ok(JsonResultData.successResultBuilder()
                    .data(inquiryResponseSlice)
                    .build());
        }
    }

    //문의사항 조회
    @GetMapping(value = "/inquiry/{inquiry_id}")
    @ApiOperation(value = "문의사항 상세 조회")
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
    public ResponseEntity<?> createInquiry(@RequestBody InquiryRequest inquiryRequest) {
        Long inquiryId = inquiryService.createInquiry(inquiryRequest).getId();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(inquiryId)
                        //리다이렉션 으로 '상세조회 호출'
                        .build());
    }

    //문의사항 수정
    @ApiOperation(value = "문의사항 수정 API")
    @PatchMapping(value = "/inquiry")
    public ResponseEntity<?> updateInquiry(@RequestBody InquiryRequest inquiryRequest) {
        Long inquiryId = inquiryService.updateInquiry(inquiryRequest).getId();
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonResultData.successResultBuilder()
                        .data(inquiryId)
                        //리다이렉션 으로 '상세조회 호출'
                        .build());
    }

    //문의사항 삭제
    @ApiOperation(value = "문의사항 삭제 API")
    @DeleteMapping(value = "/inquiry/{inquiry_id}")
    public ResponseEntity<?> deleteInquiry(@PathVariable(name = "inquiry_id") Long id) {
        inquiryService.deleteInquiry(id);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                //문의사항 초기화면으로 이동
                .build());
    }

    @ApiOperation(value = "답변 조회 API")
    @GetMapping(value = "/answer/{answer_id}")
    public ResponseEntity<?> getAnswer(@PathVariable(name = "answer_id") Long id){
        AnswerDetail answerDetail = inquiryService.getAnswer(id);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(answerDetail)
                .build());
    }

    @ApiOperation(value = "답변 생성 API")
    @PostMapping(value = "/answer")
    public ResponseEntity<?> createAnswer(@RequestBody AnswerRequest answerRequest) {
        //Answer answer = inquiryService.createAnswer(answerRequest);
        Long inquiryId = inquiryService.createAnswer(answerRequest).getInquiry().getId();
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(inquiryId)
                //리다이렉션 으로 '상세조회 호출' -> 댓글 작성 시 비동기로 작업 진행해야 한다고 생각하기 때문에. 해당 컴포넌트만 바꿔주면 되기 때문
                .build()
        );
    }

    @ApiOperation(value = "답변 수정 API")
    @PatchMapping(value = "/answer")
    public ResponseEntity<?> updateAnswer(@RequestBody AnswerRequest answerRequest) {
        //Answer answer = inquiryService.updateAnswer(answerRequest);
        Long answerId = inquiryService.updateAnswer(answerRequest).getId();
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(answerId)
                //리다이렉션 으로 '상세조회 호출' -> 댓글 수정 시 비동기로 작업 진행해야 한다고 생각하기 때문에. 해당 컴포넌트만 바꿔주면 되기 때문
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
