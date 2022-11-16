package com.co.kr.modyeo.common.handler;

import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");

        if (exception == null) {
            setResponse(response, AuthErrorCode.UNKNOWN_ERROR);
        }
        //패스워드 불일치 시
        else if (exception.equals(AuthErrorCode.NOT_MATCH_PASSWORD.getCode())){
            setResponse(response, AuthErrorCode.NOT_MATCH_PASSWORD);
        }
        //잘못된 타입의 토큰인 경우
        else if (exception.equals(AuthErrorCode.WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, AuthErrorCode.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if (exception.equals(AuthErrorCode.EXPIRED_TOKEN.getCode())) {
            setResponse(response, AuthErrorCode.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if (exception.equals(AuthErrorCode.UNSUPPORTED_TOKEN.getCode())) {
            setResponse(response, AuthErrorCode.UNSUPPORTED_TOKEN);
        } else {
            setResponse(response, AuthErrorCode.ACCESS_DENIED);
        }
    }

    private void setResponse(HttpServletResponse response, AuthErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonResultData<Object> data = JsonResultData.failResultBuilder()
                .errorMessage(errorCode.getMessage())
                .errorCode(errorCode.getCode())
                .build();

        response.getWriter().print(objectMapper.writeValueAsString(data));
    }
}
