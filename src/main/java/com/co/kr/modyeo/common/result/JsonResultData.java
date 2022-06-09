package com.co.kr.modyeo.common.result;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JsonResultData<T> {

    private T data;

    private ErrorInfo error;

    @Getter
    public static class ErrorInfo{
        private String code;
        private String message;

        @Builder
        public ErrorInfo(String code, String message){
            this.code = code;
            this.message = message;
        }
    }

    @Builder(builderClassName = "successResultBuilder", builderMethodName = "successResultBuilder")
    public JsonResultData(T data) {
        this.data = data;
        this.error = null;
    }

    @Builder(builderClassName = "failResultBuilder", builderMethodName = "failResultBuilder")
    public JsonResultData(String errorCode, String errorMessage) {
        this.data = null;
        this.error = ErrorInfo.builder()
                .code(errorCode)
                .message(errorMessage)
                .build();
    }


}
