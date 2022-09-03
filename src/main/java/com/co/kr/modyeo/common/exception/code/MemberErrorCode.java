package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum MemberErrorCode {
    FAIL_UPDATE_MEMBER("FAIL_UPDATE_MEMBER", "멤버 수정 실패했습니다."),

    FAIL_CREATE_MEMBER("FAIL_CREATE_MEMBER", "멤버 생성 실패했습니다."),

    OVERLAP_MEMBER("OVERLAP_MEMBER", "멤버 이름 중복으로 등록 실패했습니다."),

    NOT_FOUND_MEMBER("NOT_FOUND_MEMBER", "멤버 찾지 못했습니다."),

    ENTERED_EMAIL_AND_PASSWORD("ENTERED_EMAIL_AND_PASSWORD", "이메일과 패스워드를 입력해주세요");
    private final String code;
    private final String message;

    MemberErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
