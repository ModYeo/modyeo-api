package com.co.kr.modyeo.api.member.auth.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordUpdateRequest {

    private Long memberId;

    private String password;
}
