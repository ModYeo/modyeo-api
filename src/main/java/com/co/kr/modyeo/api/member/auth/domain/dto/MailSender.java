package com.co.kr.modyeo.api.member.auth.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailSender {
    private String address;
    private String title;
    private String message;
}
