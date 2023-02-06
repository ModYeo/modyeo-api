package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

import javax.management.loading.MLetContent;

@Getter
public enum AreaErrorCode {
    NOT_FOUND_AREA("NOT_FOUND_AREA", "지역을 찾지 못했습니다.");

    private final String code;

    private final String message;

    AreaErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
