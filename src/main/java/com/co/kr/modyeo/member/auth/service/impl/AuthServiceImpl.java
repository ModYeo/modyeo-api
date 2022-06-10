package com.co.kr.modyeo.member.auth.service.impl;

import com.co.kr.modyeo.common.exception.CustomAuthException;
import com.co.kr.modyeo.common.exception.ErrorCode;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.member.auth.domain.dto.MemberRequestDto;
import com.co.kr.modyeo.member.auth.domain.dto.MemberResponseDto;
import com.co.kr.modyeo.member.auth.domain.dto.TokenDto;
import com.co.kr.modyeo.member.auth.domain.dto.TokenRequestDto;
import com.co.kr.modyeo.member.auth.domain.entity.RefreshToken;
import com.co.kr.modyeo.member.auth.provider.JwtTokenProvider;
import com.co.kr.modyeo.member.auth.repository.RefreshTokenRepository;
import com.co.kr.modyeo.member.auth.service.AuthService;
import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())){
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(ErrorCode.ALREADY_JOIN_USER.getCode())
                    .errorMessage(ErrorCode.ALREADY_JOIN_USER.getMessage())
                    .build());
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.toResponse(memberRepository.save(member));
    }

    @Override
    public TokenDto login(MemberRequestDto memberRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Override
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(ErrorCode.NOT_VALID_TOKEN.getCode())
                    .errorMessage(ErrorCode.NOT_VALID_TOKEN.getMessage())
                    .build());
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(()-> new CustomAuthException(JsonResultData
                .failResultBuilder()
                .errorCode(ErrorCode.LOG_OUT_USER.getCode())
                .errorMessage(ErrorCode.LOG_OUT_USER.getMessage())
                .build()));

        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())){
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(ErrorCode.NOT_MATCH_TOKEN_INFO.getCode())
                    .errorMessage(ErrorCode.NOT_MATCH_TOKEN_INFO.getMessage())
                    .build());
        }

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);

        refreshToken.updateValue(tokenDto.getRefreshToken());

        return tokenDto;
    }
}
