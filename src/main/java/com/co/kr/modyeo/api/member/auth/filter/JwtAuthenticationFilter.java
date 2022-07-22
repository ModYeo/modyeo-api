package com.co.kr.modyeo.api.member.auth.filter;

import com.co.kr.modyeo.api.member.auth.provider.JwtTokenProvider;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.AuthErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;

    private final StringRedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest)request);
        try{
            if (StringUtils.hasText(token) && jwtTokenProvider.validateTokenFilter(token)){
                String isLogout = (String)redisTemplate.opsForValue().get(token);
                if (ObjectUtils.isEmpty(isLogout)) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            request.setAttribute("exception", AuthErrorCode.WRONG_TYPE_TOKEN.getCode());
        }catch (ExpiredJwtException e) {
            request.setAttribute("exception", AuthErrorCode.EXPIRED_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", AuthErrorCode.UNSUPPORTED_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", AuthErrorCode.WRONG_TOKEN.getCode());
        } catch (Exception e){
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("token : {}", token);
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
            request.setAttribute("exception", AuthErrorCode.UNKNOWN_ERROR.getCode());
        }

        chain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }
}
