package com.co.kr.modyeo.config;

import com.co.kr.modyeo.api.member.auth.filter.JwtAuthenticationFilter;
import com.co.kr.modyeo.api.member.auth.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter customFilter = new JwtAuthenticationFilter(jwtTokenProvider,stringRedisTemplate);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
