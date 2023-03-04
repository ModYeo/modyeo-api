package com.co.kr.modyeo.api.member.auth.domain.spec;

import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import com.co.kr.modyeo.common.spec.AbstractSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AdminSpecification extends AbstractSpecification<Authentication> {

    @Override
    public boolean isSatisfiedBy(Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Authority.ROLE_ADMIN.toString()));
    }

    @Override
    public void check(Authentication authentication) throws ApiException {
        if (!isSatisfiedBy(authentication)){
            throw ApiException.builder()
                    .status(HttpStatus.FORBIDDEN)
                    .errorMessage(AuthErrorCode.PERMISSION_DENIED.getMessage())
                    .errorCode(AuthErrorCode.PERMISSION_DENIED.getCode())
                    .build();
        }
    }
}
