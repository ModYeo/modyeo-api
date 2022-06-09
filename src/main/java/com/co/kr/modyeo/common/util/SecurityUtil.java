package com.co.kr.modyeo.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {
    public static Long getCurrentMemberId(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null){
            throw new RuntimeException("Security Context에 인증정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }
}
