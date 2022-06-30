package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum CrewErrorCode {
    FAIL_UPDATE_CREW("FAIL_UPDATE_CREW","크루 수정 실패했습니다."),

    FAIL_CREATE_CREW("FAIL_CREATE_CREW","크루 생성 실패했습니다."),

    OVERLAP_CREW("OVERLAP_CREW","크루 이름 중복으로 등록 실패했습니다."),

    NOT_FOUND_CREW("NOT_FOUND_CREW","크루 찾지 못했습니다.");
    private final String code;
    private final String message;

    CrewErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
