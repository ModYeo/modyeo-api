package com.co.kr.modyeo.api.member.auth.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationUserDto {
    private String email;
    private Long memberId;
    private String name;
}
