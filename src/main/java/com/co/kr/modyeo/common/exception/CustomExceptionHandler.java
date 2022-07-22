package com.co.kr.modyeo.common.exception;

import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, final ApiException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(JsonResultData.failResultBuilder()
                        .errorCode(e.getErrorEntity().getError().getCode())
                        .errorMessage(e.getErrorEntity().getError().getMessage())
                        .build());
    }

    @ExceptionHandler({CustomAuthException.class})
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, final CustomAuthException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(JsonResultData.failResultBuilder()
                        .errorCode(e.getErrorEntity().getError().getCode())
                        .errorMessage(e.getErrorEntity().getError().getMessage())
                        .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, final MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : allErrors) {
            stringBuilder.append(error.getDefaultMessage()).append(" ");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(JsonResultData.failResultBuilder()
                        .errorCode(AuthErrorCode.BAD_REQUEST_BODY.getCode())
                        .errorMessage(stringBuilder.toString())
                        .build());
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, final UsernameNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(JsonResultData.failResultBuilder()
                        .errorCode("NOT_FOUND_EMAIL")
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, final ExpiredJwtException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(JsonResultData.failResultBuilder()
                        .errorCode(AuthErrorCode.EXPIRED_TOKEN.getCode())
                        .errorMessage(AuthErrorCode.EXPIRED_TOKEN.getMessage())
                        .build());
    }

    @ExceptionHandler({UnsupportedJwtException.class})
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, final UnsupportedJwtException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(JsonResultData.failResultBuilder()
                        .errorCode(AuthErrorCode.UNSUPPORTED_TOKEN.getCode())
                        .errorMessage(AuthErrorCode.UNSUPPORTED_TOKEN.getMessage())
                        .build());
    }
}
