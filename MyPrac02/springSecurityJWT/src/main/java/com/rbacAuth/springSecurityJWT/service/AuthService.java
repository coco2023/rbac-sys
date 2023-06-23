package com.rbacAuth.springSecurityJWT.service;

import com.rbacAuth.springSecurityJWT.dto.JwtUser;
import com.rbacAuth.springSecurityJWT.dto.LoginRequest;
import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.entity.UserRole;
import com.rbacAuth.springSecurityJWT.repository.RoleRepository;
import com.rbacAuth.springSecurityJWT.repository.UserRoleRepository;
import com.rbacAuth.springSecurityJWT.util.CurrentUserUtils;
import com.rbacAuth.springSecurityJWT.util.JwtTokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2

public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    public String createToken(LoginRequest loginRequest) {

        // find usr by userName
        User user = userService.findUserByName(loginRequest.getUserName())
                .orElseThrow(
                        () -> new UsernameNotFoundException("Please register first!")
                );
        log.info("This is logging user info:" + user);

        // new a Jwt User with the logging user info (need to check the userRole)
        JwtUser jwtUser = new JwtUser(user);

        // check if user is able  to log(not in the block list)
        if (!jwtUser.getEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }

        /**
         * V2: get RoleName throw userId, between UserRole Table, RoleTable; return as List
         */
        List<String> roleNames = userRoleRepository.findByUserId(user.getUserId())
                .stream()
                .map(UserRole::getRoleId)
                .map(role -> roleRepository.findById(role).get().getRoleName())
                .collect(Collectors.toList());
        log.info("UserRole Table: " + roleNames);

        // add roleNames to authority
        List<String> authorities = roleNames.stream()
                .map(roleName -> new SimpleGrantedAuthority(roleName))
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        log.info("authorities Table: " + authorities);

        // create JWT Token
        String token = JwtTokenUtils.createToken(
                user.getUserName(),
                String.valueOf(user.getUserId()),
                authorities,
                loginRequest.getRememberMe()
        );
        log.info("This is JWT token:" + token);

//        // using Redis to store temporary data
        stringRedisTemplate.opsForValue().set(String.valueOf(user.getUserId()), token);

        return token;
    }

    public void removeToken() {
        long userId = currentUserUtils.getCurrentUser().getUserId();
        log.info("userId: " + userId);
        stringRedisTemplate.delete(String.valueOf(userId));
        log.info("Logout Success!");
    }
}
