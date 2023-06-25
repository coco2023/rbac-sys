package com.rbacAuth.springSecurityJWT.filter;

import com.rbacAuth.springSecurityJWT.constant.SecurityConstants;
import com.rbacAuth.springSecurityJWT.util.JwtTokenUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate) {
        super(authenticationManager);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }

        String tokenValue = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        UsernamePasswordAuthenticationToken authentication = null;
        log.info("tokenValue: " + tokenValue);

        try {
            String previousToken = stringRedisTemplate.opsForValue().get(JwtTokenUtils.getId(tokenValue));
            log.info("previousToken: " + previousToken);
            if (!token.equals(previousToken)) {
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                log.info("chain: " + chain);
                return;
            }
            authentication = JwtTokenUtils.getAuthentication(tokenValue);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}

