package com.co.kr.modyeo.api.member.auth.service.impl;

import com.co.kr.modyeo.api.member.auth.domain.dto.*;
import com.co.kr.modyeo.common.provider.JwtTokenProvider;
import com.co.kr.modyeo.api.member.auth.service.AuthService;
import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.api.member.collection.repository.CollectionInfoRepository;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberCollectionInfo;
import com.co.kr.modyeo.api.member.repository.MemberCollectionInfoRepository;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.CustomAuthException;
import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.common.util.ModyeoMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final CollectionInfoRepository collectionInfoRepository;

    private final MemberCollectionInfoRepository memberCollectionInfoRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final StringRedisTemplate redisTemplate;

    private final ModyeoMailSender modyeoMailSender;

    @Override
    public MemberResponseDto signup(MemberJoinDto memberJoinDto) {
        if (memberRepository.existsByEmail(memberJoinDto.getEmail())) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.ALREADY_JOIN_USER.getCode())
                    .errorMessage(AuthErrorCode.ALREADY_JOIN_USER.getMessage())
                    .build());
        }

        Member member = MemberJoinDto.toMember(memberJoinDto, passwordEncoder);
        member = memberRepository.save(member);

        if (memberJoinDto.getCollectionInfoDtoList() != null && memberJoinDto.getCollectionInfoDtoList().size() > 0){
            List<Long> collectionIdList = MemberJoinDto.CollectionInfoDto.getIdList(memberJoinDto.getCollectionInfoDtoList());

            List<CollectionInfo> collectionInfoList = collectionInfoRepository.findByIdList(collectionIdList);

            Member finalMember = member;
            List<MemberCollectionInfo> memberCollectionInfoList = collectionInfoList.stream().map(collectionInfo ->{
                        MemberJoinDto.CollectionInfoDto collectionInfoDto = memberJoinDto.getCollectionInfo(collectionInfo.getId());
                        return MemberCollectionInfo.createMemberCollectionInfoBuilder()
                                .member(finalMember)
                                .collectionInfo(collectionInfo)
                                .agreeYn(collectionInfoDto.getAgreeYn())
                                .build();
                    })
                    .collect(Collectors.toList());

            memberCollectionInfoRepository.saveAll(memberCollectionInfoList);
        }

        return MemberResponseDto.toResponse(member);
    }

    @Override
    @Transactional
    public TokenDto login(MemberLoginDto memberLoginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginDto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String refreshToken = redisTemplate.opsForValue().get("RT:" + authentication.getName());
        if (refreshToken != null){
            redisTemplate.delete("RT:" + authentication.getName());

            Optional<Member> optionalMember = memberRepository.findByEmail(authentication.getName());
            if (optionalMember.isPresent()){
                Member member = optionalMember.get();
                redisTemplate.opsForValue()
                        .set(member.getLastAccessToken(), "logout", 1000 * 60 * 60, TimeUnit.MILLISECONDS);
            }
        }

        return getTokenDto(authentication);
    }

    @Override
    @Transactional
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
        if (refreshToken == null || !refreshToken.equals(tokenRequestDto.getRefreshToken())) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.NOT_MATCH_TOKEN_INFO.getCode())
                    .errorMessage(AuthErrorCode.NOT_MATCH_TOKEN_INFO.getMessage())
                    .build());
        }

        return getTokenDto(authentication);
    }

    @Override
    public void logout(TokenRequestDto tokenRequestDto) {
        if (!jwtTokenProvider.validateToken(tokenRequestDto.getAccessToken())) {
            throw new CustomAuthException(JsonResultData
                    .failResultBuilder()
                    .errorCode(AuthErrorCode.NOT_VALID_TOKEN.getCode())
                    .errorMessage(AuthErrorCode.NOT_VALID_TOKEN.getMessage())
                    .build());
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        if (redisTemplate.opsForValue().get("RT:" + authentication.getName()) != null) {
            redisTemplate.delete("RT:" + authentication.getName());
        }

        Long expiration = jwtTokenProvider.getExpiration(tokenRequestDto.getAccessToken());
        redisTemplate.opsForValue()
                .set(tokenRequestDto.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
    }

    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        Member member = memberRepository.findByEmail(passwordUpdateRequest.getEmail())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .build());

        member.changePassword(passwordUpdateRequest.getPassword(), passwordEncoder);
    }

    @Override
    public void authMail(String email,String authNumber) {
        MailDto mailDto = MailDto.makeAuthSender(email, authNumber);
        modyeoMailSender.send(mailDto);
    }

    @Override
    public String checkOverlapNickname(String nickname) {
        return memberRepository.existsByNickname(nickname)? "disable" : "enable";
    }

    private TokenDto getTokenDto(Authentication authentication) {
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);

        redisTemplate.opsForValue()
                .set("RT:" + authentication.getName(),
                        tokenDto.getRefreshToken(),
                        tokenDto.getAccessTokenExpiresIn(),
                        TimeUnit.MILLISECONDS);

        Optional<Member> optionalMember = memberRepository.findByEmail(authentication.getName());
        if (optionalMember.isPresent()){
            Member member = optionalMember.get();
            member.changeLastAccessToken(tokenDto.getAccessToken());
        }

        return tokenDto;
    }
}
