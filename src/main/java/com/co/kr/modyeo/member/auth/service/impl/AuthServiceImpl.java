package com.co.kr.modyeo.member.auth.service.impl;

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
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
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
            throw new RuntimeException("Refresh Token이 유요하지 않습니다.");
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(()->new RuntimeException("로그아웃된 사용자 입니다."));

        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())){
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);

        refreshToken.updateValue(tokenDto.getRefreshToken());

        return tokenDto;
    }
}
