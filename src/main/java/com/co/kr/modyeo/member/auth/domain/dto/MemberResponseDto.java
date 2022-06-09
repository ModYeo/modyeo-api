package com.co.kr.modyeo.member.auth.domain.dto;

import com.co.kr.modyeo.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private String email;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public MemberResponseDto(String email) {
        this.email = email;
    }

    public static MemberResponseDto toResponse(Member member){
        return MemberResponseDto.of()
                .email(member.getEmail())
                .build();
    }
}
