package com.rbacAuth.springSecurityJWT.service;

import com.rbacAuth.springSecurityJWT.dto.UserRegisterRequest;
import com.rbacAuth.springSecurityJWT.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser();

    void save(UserRegisterRequest userRegisterRequest,String roleType);

    Optional<User> findByUserName(String userName);

}
