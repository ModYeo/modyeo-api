package com.co.kr.modyeo.common.util;

import com.co.kr.modyeo.api.member.auth.domain.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSender {

    private JavaMailSender javaMailSender;

    private static final String FROM_ADDRESS = "";

    public void send(MailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        javaMailSender.send(message);
    }
}
