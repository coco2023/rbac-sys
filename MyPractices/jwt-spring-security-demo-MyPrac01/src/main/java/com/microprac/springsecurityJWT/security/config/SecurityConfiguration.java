package com.microprac.springsecurityJWT.security.config;

import com.microprac.springsecurityJWT.security.common.constant.SecurityConstants;
import com.microprac.springsecurityJWT.security.exception.JwtAccessDeniedHandler;
import com.microprac.springsecurityJWT.security.exception.JwtAuthenticationEntryPoint;
import com.microprac.springsecurityJWT.security.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Spring Security配置类
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults())
                // forbidden CSRF
                .csrf().disable()
                .authorizeRequests()
                // permit the white list's ports
                .antMatchers(SecurityConstants.SWAGGER_WHITELIST).permitAll()
                .antMatchers(SecurityConstants.H2_CONSOLE).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.SYSTEM_WHITELIST).permitAll()
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

        // avoid block of H2 webpage Frame
        http.headers().frameOptions().disable();
    }

    /**
     * Cors configuration optimization
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(singletonList("*"));
        // configuration.setAllowedOriginPatterns(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setExposedHeaders(singletonList(SecurityConstants.TOKEN_HEADER));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
