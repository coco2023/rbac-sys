package com.authsys.SpringSecurity.service;

import com.authsys.SpringSecurity.common.util.CurrentUserUtils;
import com.authsys.SpringSecurity.common.util.JwtTokenUtils;
import com.authsys.SpringSecurity.entity.JwtUser;
import com.authsys.SpringSecurity.entity.User;
import com.authsys.SpringSecurity.model.LoginRequest;
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

    public String createToken(LoginRequest loginRequest) {
        User user = userService.find(loginRequest.getUserName());

        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("The user name or password is not correct.");
        }

        JwtUser jwtUser = new JwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }

        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String token = JwtTokenUtils.createToken(user.getUserName(), user.getId().toString(), authorities, loginRequest.getRememberMe());

        stringRedisTemplate.opsForValue().set(user.getId().toString(), token);

        return token;
    }

    public void removeToken() {
        stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getId().toString());
    }
}
