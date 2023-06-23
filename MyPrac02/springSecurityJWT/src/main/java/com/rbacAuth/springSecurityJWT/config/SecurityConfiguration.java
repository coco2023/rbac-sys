package com.rbacAuth.springSecurityJWT.config;

import com.rbacAuth.springSecurityJWT.constant.SecurityConstants;
import com.rbacAuth.springSecurityJWT.exception.JwtAccessDeniedHandler;
import com.rbacAuth.springSecurityJWT.exception.JwtAuthenticationEntryPoint;
import com.rbacAuth.springSecurityJWT.filter.JwtAuthorizationFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Log4j2

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private StringRedisTemplate stringRedisTemplate;

    public SecurityConfiguration(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults())
                // forbidden CSRF
                .csrf().disable()
                .authorizeRequests()
//                .anyRequest().permitAll()   // auth all url
                // permit the white list's ports
                .antMatchers(HttpMethod.POST, SecurityConstants.SYSTEM_WHITELIST).permitAll()
//                .antMatchers("user_dashboard").hasAuthority("USER")
                // other ports need auth
                .anyRequest().authenticated()

                .and()
                // add user-defined filter
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), stringRedisTemplate))
                // no need session（不创建会话）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                // auth exception handler
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler());

        log.info("Successfully auth!!");
        // avoid block of H2 webpage Frame
        http.headers().frameOptions().disable();
    }
}
