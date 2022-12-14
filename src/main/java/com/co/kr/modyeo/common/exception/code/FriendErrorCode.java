package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum FriendErrorCode {

    SENDER_NOT_FOUND("SENDER_NOT_FOUND", "요청 발송자를 찾지 못했습니다."),
    RECEIVER_NOT_FOUND("RECEIVER_NOT_FOUND", "요청 수신자를 찾지 못했습니다."),
    FRIEND_NOT_FOUND("FRIEND_NOT_FOUND", "친구를 찾지 못했습니다.");

    private final String code;
    private final String message;

    FriendErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
