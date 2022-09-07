package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum AuthErrorCode {

    WRONG_TOKEN("WRONG_TOKEN", "잘못된 토큰입니다."),

    UNKNOWN_ERROR("UNKNOWN_ERROR", "알 수 없는 에러발생"),

    EXPIRED_TOKEN("EXPIRED_TOKEN", "기간이 만료된 토큰입니다."),

    UNSUPPORTED_TOKEN("UNSUPPORTED_TOKEN", "지원하지 않는 방식의 토큰입니다."),

    WRONG_TYPE_TOKEN("WRONG_TYPE_TOKEN", "잘못된 타입의 토큰입니다."),

    ACCESS_DENIED("ACCESS_DENIED", "접근 거부되었습니다."),

    SECURITY_CONTEXT_NOT_FOUND("SECURITY_CONTEXT_NOT_FOUND", "Security Context에 인증정보가 없습니다."),
    NOT_AUTH_TOKEN("NOT_AUTH_TOKEN", "권한 정보가 없는 토근입니다."),
    ALREADY_JOIN_USER("ALREADY_JOIN_USER", "이미 가입되어 있는 유저입니다."),
    NOT_VALID_TOKEN("NOT_VALID_TOKEN", "Token이 유효하지 않습니다."),

    LOG_OUT_USER("LOG_OUT_USER", "로그아웃된 사용자 입니다."),

    NOT_MATCH_TOKEN_INFO("NOT_MATCH_TOKEN_INFO", "토큰의 유저 정보가 일치하지 않습니다."),
    BAD_REQUEST_BODY("BAD_REQUEST_BODY", ""),

    NOT_MATCH_PASSWORD("NOT_MATCH_PASSWORD", "패스워드가 일치하지 않습니다.");

    private final String code;
    private final String message;

    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
