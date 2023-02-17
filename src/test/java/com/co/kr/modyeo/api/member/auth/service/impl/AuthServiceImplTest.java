package com.co.kr.modyeo.api.member.auth.service.impl;

import com.co.kr.modyeo.api.member.auth.service.AuthService;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {

    @Test
    void validPassword() {
        String password = "12!@!@D";
        if (password.length() <= 8 || password.length() >= 16){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(AuthErrorCode.PASSWORD_NOT_ENOUGH_CONDITION.getCode())
                    .errorMessage(AuthErrorCode.PASSWORD_NOT_ENOUGH_CONDITION.getMessage())
                    .build();
        }

        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$",password)){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(AuthErrorCode.PASSWORD_NOT_ENOUGH_CONDITION.getCode())
                    .errorMessage(AuthErrorCode.PASSWORD_NOT_ENOUGH_CONDITION.getMessage())
                    .build();
        }
    }
}