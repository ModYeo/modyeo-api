package com.co.kr.modyeo.member.auth.service;

import com.co.kr.modyeo.member.auth.domain.dto.MemberRequestDto;
import com.co.kr.modyeo.member.auth.domain.dto.MemberResponseDto;
import com.co.kr.modyeo.member.auth.domain.dto.TokenDto;
import com.co.kr.modyeo.member.auth.domain.dto.TokenRequestDto;

public interface AuthService {
    MemberResponseDto signup(MemberRequestDto memberRequestDto);

    TokenDto login(MemberRequestDto memberRequestDto);

    TokenDto reissue(TokenRequestDto tokenRequestDto);

    void logout(TokenRequestDto tokenRequestDto);
}
