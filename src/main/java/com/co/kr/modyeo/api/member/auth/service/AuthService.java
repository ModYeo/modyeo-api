package com.co.kr.modyeo.api.member.auth.service;

import com.co.kr.modyeo.api.member.auth.domain.dto.*;

public interface AuthService {
    MemberResponseDto signup(MemberJoinDto memberJoinDto);

    TokenDto login(MemberLoginDto memberLoginDto);

    TokenDto reissue(TokenRequestDto tokenRequestDto);

    void logout(TokenRequestDto tokenRequestDto);
}
