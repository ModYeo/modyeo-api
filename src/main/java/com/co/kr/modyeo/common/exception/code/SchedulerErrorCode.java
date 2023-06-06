package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum SchedulerErrorCode {

    RECRUITMENT_COUNT_OVER("RECRUITMENT_COUNT_OVER","현재 신청 인원보다 수용인원이 많습니다."),
    NOT_FOUND_SCHEDULER("NOT_FOUND_SCHEDULER", "매칭을 찾을 수 없습니다.");

    private final String code;

    private final String message;

    SchedulerErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
