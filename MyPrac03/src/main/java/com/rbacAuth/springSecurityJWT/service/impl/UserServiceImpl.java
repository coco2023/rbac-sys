package com.rbacAuth.springSecurityJWT.service.impl;

import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.repository.UserRepository;
import com.rbacAuth.springSecurityJWT.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser() {

        return userRepository.findAll();
    }
}
