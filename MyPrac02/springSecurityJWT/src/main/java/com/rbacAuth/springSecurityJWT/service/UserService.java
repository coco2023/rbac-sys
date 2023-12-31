package com.rbacAuth.springSecurityJWT.service;

import com.rbacAuth.springSecurityJWT.constant.UserRoleEnum;
import com.rbacAuth.springSecurityJWT.dto.UserRegisterRequest;
import com.rbacAuth.springSecurityJWT.dto.UserRepresentation;
import com.rbacAuth.springSecurityJWT.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveNormalUser(UserRegisterRequest userRegisterRequest);

    List<UserRepresentation> getAll();

    void save(UserRegisterRequest userRegisterRequest, UserRoleEnum userRole);

    Optional<User> findUserByName(String username);
}
