package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum AuthErrorCode {
    SECURITY_CONTEXT_NOT_FOUND("SECURITY_CONTEXT_NOT_FOUND","Security Context에 인증정보가 없습니다."),
    NOT_AUTH_TOKEN("NOT_AUTH_TOKEN","권한 정보가 없는 토근입니다."),
    ALREADY_JOIN_USER("ALREADY_JOIN_USER","이미 가입되어 있는 유저입니다."),
    NOT_VALID_TOKEN("NOT_VALID_TOKEN","Token이 유효하지 않습니다."),

    LOG_OUT_USER("LOG_OUT_USER","로그아웃된 사용자 입니다."),

    NOT_MATCH_TOKEN_INFO("NOT_MATCH_TOKEN_INFO","토큰의 유저 정보가 일치하지 않습니다."),
    BAD_REQUEST_BODY("BAD_REQUEST_BODY","");

    private final String code;
    private final String message;

    AuthErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
