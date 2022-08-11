package com.co.kr.modyeo.api.member.controller;

import com.co.kr.modyeo.api.member.domain.dto.request.MemberCategoryRequest;
import com.co.kr.modyeo.api.member.domain.dto.request.MemberProfilePathRequest;
import com.co.kr.modyeo.api.member.domain.dto.request.NicknameUpdateRequest;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;
import com.co.kr.modyeo.api.member.service.MemberService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/member")
@Api("회원 API Controller")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @ApiOperation(value = "회원 카테고리 정보 생성 API")
    @PostMapping("")
    public ResponseEntity<?> createMemberInfo(@RequestBody MemberCategoryRequest memberCategoryRequest) {
        memberService.createMemberInfo(memberCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(null)
                        .build());
    }

    @ApiOperation(value = "회원 상세 조회 API")
    @GetMapping("/{member_id}")
    public ResponseEntity<?> getMember(
            @PathVariable(value = "member_id") Long memberId) {
        MemberDetail memberDetail = memberService.getMember(memberId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(memberDetail)
                .build());
    }

    @ApiOperation(value = "회원 닉네임 변경 API")
    @PatchMapping("/nickname")
    public ResponseEntity<?> updateNickname(
            @RequestBody NicknameUpdateRequest nicknameUpdateRequest
    ) {
        memberService.updateNickname(nicknameUpdateRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "프로필 등록/변경 API")
    @PutMapping("/profile-path")
    public ResponseEntity<?> putProfilePath(@RequestBody MemberProfilePathRequest memberProfilePathRequest){
        memberService.putProfilePath(memberProfilePathRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }
}
