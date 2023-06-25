package com.rbacAuth.springSecurityJWT.service;

import com.rbacAuth.springSecurityJWT.dto.UserLoginRequest;
import com.rbacAuth.springSecurityJWT.entity.JwtUser;
import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.asecurity.util.CurrentUserUtils;
import com.rbacAuth.springSecurityJWT.asecurity.util.JwtTokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2

public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    public String createToken(UserLoginRequest userLoginRequest) {
        User user = userService.findByUserName(userLoginRequest.getUserName()).get();

        if (!isPasswordMatch(userLoginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("The user name or password is not correct.");
        }

        // create JWT User
        JwtUser jwtUser = new JwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }

        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // create JWT token
        String token = JwtTokenUtils.createToken(
                user.getUserName(),
                user.getUserId().toString(),
                authorities,
                userLoginRequest.getRememberMe()
        );

        // using Redis to keep the temp login data
        stringRedisTemplate.opsForValue().set(user.getUserId().toString(), token);
        log.info("stringRedisTemplate: " + stringRedisTemplate);

        return token;
    }

    private boolean isPasswordMatch(String inputPassword, String dbPassword) {
        return inputPassword.equals(dbPassword);
    }

    public void removeToken() {
        User currentUser = currentUserUtils.getCurrentUser();
        stringRedisTemplate.delete(currentUser.getUserId().toString());
    }
}
