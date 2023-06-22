package com.rbacAuth.springSecurityJWT.service.impl;

import com.rbacAuth.springSecurityJWT.repository.UserRepository;
import com.rbacAuth.springSecurityJWT.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRepository userRepository;
}
