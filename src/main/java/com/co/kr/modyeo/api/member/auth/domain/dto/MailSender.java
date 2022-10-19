package com.co.kr.modyeo.api.member.auth.domain.dto;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailSender {
    private String address;

    private String title;

    private String message;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public MailSender(String address, String title, String message) {
        this.address = address;
        this.title = title;
        this.message = message;
    }

    public static MailSender makeAuthSender(Member member, String authNumber){
        return of()
                .address(member.getEmail())
                .title(getTitle())
                .message(makeMessage(member,authNumber))
                .build();
    }

    private static String makeMessage(Member member, String authNumber) {
        return member.getUsername() + "님의 인증메일이 도착했습니다.\n"+"인증번호" + authNumber;
    }

    private static String getTitle() {
        return "[본인인증] Crew-in에서 인증메일을 발송했습니다.";
    }
}
