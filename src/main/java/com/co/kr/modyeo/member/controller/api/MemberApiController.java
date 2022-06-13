package com.co.kr.modyeo.member.controller.api;

import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.member.domain.dto.response.MemberResponse;
import com.co.kr.modyeo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<?> getMembers(){
        List<MemberResponse> members = memberService.getMembers();
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(members)
                .build());
    }

}
