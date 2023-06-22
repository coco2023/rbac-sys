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
         * V1: get RoleName throw userId, between UserRole Table, RoleTable; return as List
         * get user authority from userRole Table based on the userId
         */
        // 1.1 get UserRole by userId
        long userId = user.getUserId();
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);

//        // 1.2 get RoleId list from UserRole
//        List<Long> roles = userRoles.stream()
//                .map(UserRole::getRoleId)
//                .collect(Collectors.toList());
//        log.info("roleId Table: " + roles.get(0) + ", " + roles.get(1));
//
//        // 1.3 get roleName list from Role
//        List<String> roleName = roles.stream()
//                .map(role -> roleRepository.findById(role).get().getRoleName())
//                .collect(Collectors.toList());
//
//        log.info("roleName Table: " + roleName);

        // 1.4 = 1.2 + 1.3 get Role_Name from UsreRole and Role tables
        List<String> roleNames = userRoles.stream()
                .map(UserRole::getRoleId)
                .map(role -> roleRepository.findById(role).get().getRoleName())
                .collect(Collectors.toList());
        log.info("roleNames List: " + roleNames);

        // create JWT Token
        String token = JwtTokenUtils.createToken(
                user.getUserName(),
                String.valueOf(user.getUserId()),
                roleNames,
                loginRequest.getRememberMe()
        );

        log.info("!This is token:" + token);

        return token;
    }
}
