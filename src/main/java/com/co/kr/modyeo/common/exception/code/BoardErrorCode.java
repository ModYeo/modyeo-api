package com.co.kr.modyeo.common.exception.code;

import lombok.Getter;

@Getter
public enum BoardErrorCode {

    NOT_FOUND_ARTICLE("NOT_FOUND_ARTICLE", "찾을 수 없는 게시글 입니다."),

    NOT_DELETE_AUTH("NOT_DELETE_AUTH","삭제권한이 없는 사용자 입니다."),
    NOT_FOUND_REPLY("NOT_FOUND_REPLY","찾을 수 없는 댓글 입니다.");

    private final String code;
    private final String message;

    BoardErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
