package com.co.kr.modyeo.member.auth.domain.dto;

import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.domain.enumerate.Authority;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class MemberRequestDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    public MemberRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

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
