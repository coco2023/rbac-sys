package com.rbacAuth.springSecurityJWT.service;

import com.rbacAuth.springSecurityJWT.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
}