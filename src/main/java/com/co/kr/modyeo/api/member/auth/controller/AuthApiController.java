package com.co.kr.modyeo.api.member.auth.controller;

import com.co.kr.modyeo.api.member.auth.domain.dto.*;
import com.co.kr.modyeo.api.member.auth.service.AuthService;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
@Api("인증 관련 API Controller")
@RequiredArgsConstructor
public class AuthApiController {

    private final String BASIC_PREFIX = "Basic ";
    private final AuthService authService;

    @ApiOperation(value = "회원가입 API")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @Valid @RequestBody MemberJoinDto memberJoinDto) {
        if (authorization != null) {
            String authBasic = authorization.substring(BASIC_PREFIX.length());

            String decodedAuthBasic = new String(Base64.getDecoder().decode(authBasic), StandardCharsets.UTF_8);
            String[] authUserInfo = decodedAuthBasic.split(":");

            String email = authUserInfo[0];
            String password = authUserInfo[1];

            memberJoinDto.setEmail(email);
            memberJoinDto.setPassword(password);

            MemberResponseDto memberResponseDto = authService.signup(memberJoinDto);
            return ResponseHandler.generate()
                    .data(memberResponseDto)
                    .status(HttpStatus.CREATED)
                    .build();
        }else {
            return ResponseHandler.failResultGenerate()
                    .errorMessage(MemberErrorCode.ENTERED_EMAIL_AND_PASSWORD.getMessage())
                    .errorCode(MemberErrorCode.ENTERED_EMAIL_AND_PASSWORD.getCode())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @ApiOperation(value = "로그인 API")
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (authorization != null) {
            String authBasic = authorization.substring(BASIC_PREFIX.length());

            String decodedAuthBasic = new String(Base64.getDecoder().decode(authBasic), StandardCharsets.UTF_8);
            String[] authUserInfo = decodedAuthBasic.split(":");

            String email = authUserInfo[0];
            String password = authUserInfo[1];

            MemberLoginDto memberLoginDto = MemberLoginDto.of()
                    .email(email)
                    .password(password)
                    .build();

            TokenDto tokenDto = authService.login(memberLoginDto);
            return ResponseHandler.generate()
                    .data(tokenDto)
                    .status(HttpStatus.OK)
                    .build();
        }else {
            return ResponseHandler.failResultGenerate()
                    .errorMessage(MemberErrorCode.ENTERED_EMAIL_AND_PASSWORD.getMessage())
                    .errorCode(MemberErrorCode.ENTERED_EMAIL_AND_PASSWORD.getCode())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @ApiOperation(value = "토큰 재발급 API")
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@Valid @RequestBody TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto = authService.reissue(tokenRequestDto);
        return ResponseHandler.generate()
                .data(tokenDto)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "로그아웃 API")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody TokenRequestDto tokenRequestDto) {
        authService.logout(tokenRequestDto);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "비밀번호 변경 API")
    @PatchMapping("/password")
    public ResponseEntity<?> updatePassword(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (authorization != null) {
            String authBasic = authorization.substring(BASIC_PREFIX.length());

            String decodedAuthBasic = new String(Base64.getDecoder().decode(authBasic), StandardCharsets.UTF_8);
            String[] authUserInfo = decodedAuthBasic.split(":");

            String email = authUserInfo[0];
            String password = authUserInfo[1];

            PasswordUpdateRequest passwordUpdateRequest = new PasswordUpdateRequest();
            passwordUpdateRequest.setEmail(email);
            passwordUpdateRequest.setPassword(password);

            authService.updatePassword(passwordUpdateRequest);
            return ResponseHandler.generate()
                    .data(null)
                    .status(HttpStatus.OK)
                    .build();
        }else {
            return ResponseHandler.failResultGenerate()
                    .errorMessage(MemberErrorCode.ENTERED_EMAIL_AND_PASSWORD.getMessage())
                    .errorCode(MemberErrorCode.ENTERED_EMAIL_AND_PASSWORD.getCode())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @ApiOperation(value = "메일 인증 API")
    @PostMapping("/mail")
    public ResponseEntity<?> authMail(
            @RequestParam(value = "email",name = "email",required = true)String email,
        @RequestParam(value = "authNumber",name = "authNumber",required = true)String authNumber){
        authService.authMail(email,authNumber);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }
}
