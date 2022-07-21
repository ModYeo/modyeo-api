package com.co.kr.modyeo.api.member.controller;

import com.co.kr.modyeo.api.member.domain.dto.request.MemberRequest;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;
import com.co.kr.modyeo.api.member.service.MemberService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @ApiOperation(value = "회원 카테고리 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createMemberInfo(@RequestBody MemberRequest memberRequest){
        memberService.createMemberInfo(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

    @ApiOperation(value = "회원 상세 조회")
    @GetMapping("/{member_id}")
    public ResponseEntity<?> getMember(
            @PathVariable(value = "member_id")Long memberId){
        MemberDetail memberDetail = memberService.getMember(memberId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(memberDetail)
                .build());
    }
}
