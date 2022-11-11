package com.co.kr.modyeo.common.result;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    @Builder(builderClassName = "generate", builderMethodName = "generate")
    public static ResponseEntity<JsonResultData<?>> generateResponse(Object data, HttpStatus status){
        JsonResultData<Object> result = JsonResultData.successResultBuilder()
                .data(data)
                .build();

        return new ResponseEntity<>(result, status);
    }
}
