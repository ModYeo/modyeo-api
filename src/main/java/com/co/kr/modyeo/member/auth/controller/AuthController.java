package com.co.kr.modyeo.member.auth.controller;

import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.member.auth.domain.dto.MemberRequestDto;
import com.co.kr.modyeo.member.auth.domain.dto.MemberResponseDto;
import com.co.kr.modyeo.member.auth.domain.dto.TokenDto;
import com.co.kr.modyeo.member.auth.domain.dto.TokenRequestDto;
import com.co.kr.modyeo.member.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberRequestDto memberRequestDto){
        MemberResponseDto memberResponseDto = authService.signup(memberRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(memberResponseDto)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDto memberRequestDto){
        TokenDto tokenDto = authService.login(memberRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonResultData.successResultBuilder()
                        .data(tokenDto)
                        .build());
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenRequestDto tokenRequestDto){
        TokenDto tokenDto = authService.reissue(tokenRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(JsonResultData.successResultBuilder()
                        .data(tokenDto)
                        .build());
    }
}
