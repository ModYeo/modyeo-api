package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum InquiryErrorCode {

    NOT_FOUND_INQUIRY("NOT_FOUND_INQUIRY","존재하지 않는 문의사항입니다."),
    NOT_FOUND_ANSWER("NOT_FOUND_ANSWER","존재하지 않는 답변입니다.");

    private final String code;
    private final String message;

    InquiryErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }
}
