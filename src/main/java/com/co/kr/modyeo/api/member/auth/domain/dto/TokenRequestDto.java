package com.co.kr.modyeo.api.member.auth.domain.dto;

import lombok.Getter;

@Getter
public class TokenRequestDto {

    private String accessToken;

    private String refreshToken;
}
