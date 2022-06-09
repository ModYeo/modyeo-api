package com.co.kr.modyeo.member.auth.domain.dto;

import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.domain.enumerate.Authority;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class MemberRequestDto {

    private String email;
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.of()
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
