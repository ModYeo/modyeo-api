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
public class MemberRequestDto {

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

    public MemberRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MemberRequestDto(String email, String password, String username, Address address, Sex sex, List<CategoryRequest> categoryRequests) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.sex = sex;
        this.categoryRequests = categoryRequests;
    }

    public static Member toMember(MemberRequestDto memberRequestDto,PasswordEncoder passwordEncoder){
        return Member.of()
                .email(memberRequestDto.email)
                .password(passwordEncoder.encode(memberRequestDto.password))
                .authority(Authority.ROLE_USER)
                .username(memberRequestDto.username)
                .address(memberRequestDto.address)
                .sex(memberRequestDto.sex)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
