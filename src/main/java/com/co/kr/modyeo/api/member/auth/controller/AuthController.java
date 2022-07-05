package com.co.kr.modyeo.api.member.auth.controller;

import com.co.kr.modyeo.api.member.auth.domain.dto.MemberRequestDto;
import com.co.kr.modyeo.api.member.auth.domain.dto.MemberResponseDto;
import com.co.kr.modyeo.api.member.auth.domain.dto.TokenDto;
import com.co.kr.modyeo.api.member.auth.domain.dto.TokenRequestDto;
import com.co.kr.modyeo.api.member.auth.service.AuthService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody MemberRequestDto memberRequestDto){
        MemberResponseDto memberResponseDto = authService.signup(memberRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(memberResponseDto)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody MemberRequestDto memberRequestDto){
        TokenDto tokenDto = authService.login(memberRequestDto);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                        .data(tokenDto)
                        .build());
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@Valid @RequestBody TokenRequestDto tokenRequestDto){
        TokenDto tokenDto = authService.reissue(tokenRequestDto);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                        .data(tokenDto)
                        .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody TokenRequestDto tokenRequestDto){
        authService.logout(tokenRequestDto);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }
}
