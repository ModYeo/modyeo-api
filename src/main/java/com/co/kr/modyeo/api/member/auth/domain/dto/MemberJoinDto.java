package com.co.kr.modyeo.api.member.auth.domain.dto;

import com.co.kr.modyeo.api.category.domain.dto.request.CategoryRequest;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.entity.embed.Address;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemberJoinDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    private String username;

    @Embedded
    private Address address;

    private Sex sex;

    private List<CategoryRequest> categoryRequests = new ArrayList<>();

    public MemberJoinDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MemberJoinDto(String email, String password, String username, Address address, Sex sex, List<CategoryRequest> categoryRequests) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.sex = sex;
        this.categoryRequests = categoryRequests;
    }

    public static Member toMember(MemberJoinDto memberJoinDto, PasswordEncoder passwordEncoder){
        return Member.of()
                .email(memberJoinDto.email)
                .password(passwordEncoder.encode(memberJoinDto.password))
                .authority(Authority.ROLE_USER)
                .username(memberJoinDto.username)
                .address(memberJoinDto.address)
                .sex(memberJoinDto.sex)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
