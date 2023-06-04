package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum SchedulerErrorCode {
    NOT_FOUND_SCHEDULER("NOT_FOUND_SCHEDULER", "매칭을 찾을 수 없습니다.");

    private final String code;

    private final String message;

    SchedulerErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
