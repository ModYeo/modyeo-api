package com.co.kr.modyeo.api.member.auth.service.impl;

import com.co.kr.modyeo.api.category.repository.CategoryRepository;
import com.co.kr.modyeo.api.category.service.impl.CategoryServiceImpl;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.member.auth.domain.spec.AdminSpecification;
import com.co.kr.modyeo.api.member.auth.domain.spec.PasswordSpecification;
import com.co.kr.modyeo.api.member.auth.service.AuthService;
import com.co.kr.modyeo.api.member.collection.repository.CollectionInfoRepository;
import com.co.kr.modyeo.api.member.repository.MemberActiveAreaRepository;
import com.co.kr.modyeo.api.member.repository.MemberCategoryRepository;
import com.co.kr.modyeo.api.member.repository.MemberCollectionInfoRepository;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import com.co.kr.modyeo.common.provider.JwtTokenProvider;
import com.co.kr.modyeo.common.util.ModyeoMailSender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class AuthServiceImplTest {

    private AuthService authService;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private CollectionInfoRepository collectionInfoRepository;

    @Mock
    private MemberCollectionInfoRepository memberCollectionInfoRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ModyeoMailSender modyeoMailSender;

    @Mock
    private PasswordSpecification passwordSpecification;

    @Mock
    private AdminSpecification adminSpecification;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private EmdAreaRepository emdAreaRepository;

    @Mock
    private MemberCategoryRepository memberCategoryRepository;

    @Mock
    private MemberActiveAreaRepository memberActiveAreaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authService = new AuthServiceImpl(
                authenticationManagerBuilder,
                passwordEncoder,
                memberRepository,
                collectionInfoRepository,
                memberCollectionInfoRepository,
                jwtTokenProvider,
                redisTemplate,
                modyeoMailSender,
                passwordSpecification,
                adminSpecification,
                categoryRepository,
                emdAreaRepository,
                memberCategoryRepository,
                memberActiveAreaRepository);
    }

    @Test
    void validPassword() {
        String password = "aA1!aaaa";
        if (password.length() < 8 || password.length() > 16){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(AuthErrorCode.PASSWORD_NOT_ENOUGH_CONDITION.getCode())
                    .errorMessage(AuthErrorCode.PASSWORD_NOT_ENOUGH_CONDITION.getMessage())
                    .build();
        }

        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$",password)){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(AuthErrorCode.PASSWORD_NOT_ENOUGH_CONDITION.getCode())
                    .errorMessage(AuthErrorCode.PASSWORD_NOT_ENOUGH_CONDITION.getMessage())
                    .build();
        }
    }

    @Test
    void checkOverlapEmail(){
        given(memberRepository.existsByEmail(any())).willReturn(true);
        String result = authService.checkOverlapEmail("test@email.com");

        Assertions.assertThat(result).isEqualTo("disable");
    }

    @Test
    void checkOverlapEmailEnable(){
        given(memberRepository.existsByEmail(any())).willReturn(false);
        String result = authService.checkOverlapEmail("test@email.com");

        Assertions.assertThat(result).isEqualTo("enable");
    }
}