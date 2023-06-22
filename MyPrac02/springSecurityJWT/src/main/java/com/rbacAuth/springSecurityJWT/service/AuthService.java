package com.rbacAuth.springSecurityJWT.service;

import com.rbacAuth.springSecurityJWT.dto.JwtUser;
import com.rbacAuth.springSecurityJWT.dto.LoginRequest;
import com.rbacAuth.springSecurityJWT.entity.Role;
import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.entity.UserRole;
import com.rbacAuth.springSecurityJWT.repository.RoleRepository;
import com.rbacAuth.springSecurityJWT.repository.UserRoleRepository;
import com.rbacAuth.springSecurityJWT.util.JwtTokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public String createToken(LoginRequest loginRequest) {

        // find usr by userName
        User user = userService.findUserByName(loginRequest.getUsername())
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

        // create JWT Token
        String token = JwtTokenUtils.createToken(
                user.getUserName(),
                String.valueOf(user.getUserId()),
                roleNames,
                loginRequest.getRememberMe()
        );
        log.info("This is JWT token:" + token);

//        // using Redis to store temporary data
//        stringRedisTemplate.opsForValue().set(user.getId().toString(), token);


        return token;
    }
}
