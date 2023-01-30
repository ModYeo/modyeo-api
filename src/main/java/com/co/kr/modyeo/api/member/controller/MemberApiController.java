package com.co.kr.modyeo.api.member.controller;

import com.co.kr.modyeo.api.member.domain.dto.request.*;
import com.co.kr.modyeo.api.member.domain.dto.response.ApplicationMemberDetail;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberDetail;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.api.member.service.MemberService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
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
        Long memberId = memberService.createMemberInfo(memberCategoryRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(memberId)
                .build();
    }

    @ApiOperation(value = "회원 상세 조회 API")
    @GetMapping("/{member_id}")
    public ResponseEntity<?> getMember(
            @PathVariable(value = "member_id") Long memberId) {
        MemberDetail memberDetail = memberService.getMember(memberId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(memberDetail)
                .build();
    }

    @ApiOperation(value = "회원 닉네임 변경 API")
    @PatchMapping("/nickname")
    public ResponseEntity<?> updateNickname(
            @RequestBody NicknameUpdateRequest nicknameUpdateRequest
    ) {
        Long memberId = memberService.updateNickname(nicknameUpdateRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(memberId)
                .build();
    }

    @ApiOperation(value = "프로필 등록/변경 API")
    @PatchMapping("/profile-path")
    public ResponseEntity<?> putProfilePath(@RequestBody MemberProfilePathRequest memberProfilePathRequest) {
        Long memberId = memberService.putProfilePath(memberProfilePathRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(memberId)
                .build();
    }

    @ApiOperation(value = "팀 가입 신청자 정보 보기")
    @GetMapping("/{memberId}/team-application/{teamId}")
    public ResponseEntity<?> getTeamApplicationMember(
            @PathVariable Long memberId,
            @PathVariable Long teamId) {
        ApplicationMemberDetail applicationMemberDetail = memberService.getTeamApplicationMember(memberId,teamId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(applicationMemberDetail)
                .build();
    }

    @ApiOperation(value = "회원 슬라이스 조회")
    @GetMapping("")
    public ResponseEntity<?> getMembers(MemberSearch memberSearch){
        Slice<MemberResponse> members = memberService.getMembers(memberSearch);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(members)
                .build();
    }

    @ApiOperation(value = "활동 지역 추가 API")
    @PostMapping("/active-area")
    public ResponseEntity<?> createMemberActiveArea(@RequestBody MemberActiveAreaRequest memberActiveAreaRequest){
        Long memberActiveAreaId = memberService.createMemberActiveArea(memberActiveAreaRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(null)
                .build();
    }
}
