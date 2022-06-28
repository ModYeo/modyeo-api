package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum CategoryErrorCode {

    FAIL_UPDATE_CATEGORY("FAIL_UPDATE_CATEGORY","카테고리 수정 실패했습니다."),

    FAIL_CREATE_CATEGORY("FAIL_CREATE_CATEGORY","카테고리 생성 실패했습니다."),

    OVERLAP_CATEGORY("OVERLAP_CATEGORY","카테고리 이름 중복으로 등록 실패했습니다."),

    NOT_FOUND_CATEGORY("NOT_FOUND_CATEGORY","카테고리를 찾지 못했습니다.");
    private final String code;
    private final String message;

    CategoryErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
