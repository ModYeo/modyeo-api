package com.co.kr.modyeo.api.member.auth.controller;

import com.co.kr.modyeo.api.member.auth.domain.dto.*;
import com.co.kr.modyeo.api.member.auth.service.AuthService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "회원가입 API")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody MemberJoinDto memberJoinDto){
        MemberResponseDto memberResponseDto = authService.signup(memberJoinDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JsonResultData.successResultBuilder()
                        .data(memberResponseDto)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody MemberLoginDto memberLoginDto){
        TokenDto tokenDto = authService.login(memberLoginDto);
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
