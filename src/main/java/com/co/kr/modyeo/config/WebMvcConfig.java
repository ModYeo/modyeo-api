package com.co.kr.modyeo.config;

import com.co.kr.modyeo.common.interceptor.LoggerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoggerInterceptor loggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor)
//                .excludePathPatterns("/api/auth/**")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/swagger-resources/**");
    }
}
