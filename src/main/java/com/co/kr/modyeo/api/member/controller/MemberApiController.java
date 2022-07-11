package com.co.kr.modyeo.api.member.controller;

import com.co.kr.modyeo.api.member.domain.dto.request.MemberRequest;
import com.co.kr.modyeo.api.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.api.member.service.MemberService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/all")
    public ResponseEntity<?> getMembers(){
        List<MemberResponse> members = memberService.getMembers();
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(members)
                .build());
    }

//    @PostMapping("")
//    public ResponseEntity<?> createMemberInfo(@RequestBody MemberRequest memberRequest){
//    }
}
