package com.co.kr.modyeo.member.auth.service.impl;

import com.co.kr.modyeo.common.exception.CustomAuthException;
import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.member.auth.domain.dto.MemberRequestDto;
import com.co.kr.modyeo.member.auth.domain.dto.MemberResponseDto;
import com.co.kr.modyeo.member.auth.domain.dto.TokenDto;
import com.co.kr.modyeo.member.auth.domain.dto.TokenRequestDto;
import com.co.kr.modyeo.member.auth.provider.JwtTokenProvider;
import com.co.kr.modyeo.member.auth.service.AuthService;
import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final StringRedisTemplate redisTemplate;

    @Override
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())){
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.ALREADY_JOIN_USER.getCode())
                    .errorMessage(AuthErrorCode.ALREADY_JOIN_USER.getMessage())
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

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(),
                        tokenDto.getRefreshToken(),
                        tokenDto.getAccessTokenExpiresIn(),
                        TimeUnit.MILLISECONDS);

        return tokenDto;
    }

    @Override
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.NOT_VALID_TOKEN.getCode())
                    .errorMessage(AuthErrorCode.NOT_VALID_TOKEN.getMessage())
                    .build());
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        String refreshToken = redisTemplate.opsForValue().get("RT:" + authentication.getName());
        assert refreshToken != null;
        if (!refreshToken.equals(tokenRequestDto.getRefreshToken())){
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.NOT_MATCH_TOKEN_INFO.getCode())
                    .errorMessage(AuthErrorCode.NOT_MATCH_TOKEN_INFO.getMessage())
                    .build());
        }

        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(),
                        tokenDto.getRefreshToken(),
                        tokenDto.getAccessTokenExpiresIn(), TimeUnit.MILLISECONDS);

        return tokenDto;
    }

    @Override
    public void logout(TokenRequestDto tokenRequestDto) {
        if(!jwtTokenProvider.validateToken(tokenRequestDto.getAccessToken())){
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.NOT_VALID_TOKEN.getCode())
                    .errorMessage(AuthErrorCode.NOT_VALID_TOKEN.getMessage())
                    .build());
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        if(redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null){
            redisTemplate.delete("RT:"+ authentication.getName());
        }

        Long expiration = jwtTokenProvider.getExpiration(tokenRequestDto.getAccessToken());
        redisTemplate.opsForValue()
                .set(tokenRequestDto.getAccessToken(),"logout", expiration, TimeUnit.MILLISECONDS);
    }
}
