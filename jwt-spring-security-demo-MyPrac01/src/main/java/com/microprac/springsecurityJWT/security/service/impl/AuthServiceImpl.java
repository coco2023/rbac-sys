package com.microprac.springsecurityJWT.security.service.impl;

import com.microprac.springsecurityJWT.security.common.util.CurrentUserUtils;
import com.microprac.springsecurityJWT.security.common.util.JwtTokenUtils;
import com.microprac.springsecurityJWT.security.dto.LoginRequest;
import com.microprac.springsecurityJWT.security.entity.JwtUser;
import com.microprac.springsecurityJWT.security.service.AuthService;
import com.microprac.springsecurityJWT.system.entity.User;
import com.microprac.springsecurityJWT.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class AuthServiceImpl implements AuthService {

    private UserService userService;

    private StringRedisTemplate stringRedisTemplate;

    private final CurrentUserUtils currentUserUtils;

    @Override
    public String createToken(LoginRequest loginRequest) {

        User user = userService.find(loginRequest.getUsername());
        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("The user name or password is not correct!");
        }
        JwtUser jwtUser = new JwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }
        List<String> authorities = jwtUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getId().toString(), authorities, loginRequest.getRememberMe());

        stringRedisTemplate.opsForValue()
                .set(user.getId().toString(), token);

        return token;
    }

    @Override
    public void removeToken() {
        stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getId().toString());
    }

}
