package com.co.kr.modyeo.common.exception;

import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.Getter;

@Getter
public class CustomAuthException extends RuntimeException{
    private JsonResultData errorEntity;

    public CustomAuthException(JsonResultData errorEntity){
        super(errorEntity.getError().getMessage());
        this.errorEntity = errorEntity;
    }
}
