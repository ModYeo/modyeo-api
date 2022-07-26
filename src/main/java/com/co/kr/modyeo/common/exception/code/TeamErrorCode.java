package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum TeamErrorCode {
    FAIL_UPDATE_TEAM("FAIL_UPDATE_TEAM","팀 수정 실패했습니다."),

    FAIL_CREATE_TEAM("FAIL_CREATE_TEAM","팀 생성 실패했습니다."),

    OVERLAP_TEAM("OVERLAP_TEAM","팀 이름 중복으로 등록 실패했습니다."),

    NOT_FOUND_TEAM("NOT_FOUND_TEAM","팀 찾지 못했습니다."),

    ALREADY_JOINED_TEAM("ALREADY_JOINED_TEAM","이미 가입된 팀 입니다."),

    ALREADY_APPLICANT_TEAM("ALREADY_APPLICANT_TEAM", "이미 가입 신청했습니다."),

    NOT_FOUND_APPLICANT("NOT_FOUND_APPLICANT","찾을 수 없는 가입신청입니다.");

    private final String code;
    private final String message;

    TeamErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
