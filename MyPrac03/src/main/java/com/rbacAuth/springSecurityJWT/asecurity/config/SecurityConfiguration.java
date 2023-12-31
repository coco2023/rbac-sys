package com.rbacAuth.springSecurityJWT.asecurity.config;

import com.rbacAuth.springSecurityJWT.asecurity.constant.SecurityConstants;
import com.rbacAuth.springSecurityJWT.asecurity.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults())
                // disable CSRF
                .csrf().disable()
                .authorizeRequests()
                // allow all the ports
                .antMatchers(SecurityConstants.H2_CONSOLE).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.SYSTEM_WHITELIST).permitAll()
//                .anyRequest().permitAll()
                .anyRequest().authenticated()

                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), stringRedisTemplate))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


}
