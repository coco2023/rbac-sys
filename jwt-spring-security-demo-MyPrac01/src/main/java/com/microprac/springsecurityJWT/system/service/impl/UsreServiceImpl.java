package com.microprac.springsecurityJWT.system.service.impl;

import com.google.common.collect.ImmutableMap;
import com.microprac.springsecurityJWT.system.entity.User;
import com.microprac.springsecurityJWT.system.repository.UserRepository;
import com.microprac.springsecurityJWT.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class UsreServiceImpl implements UserService {

    private static final String USERNAME = "username:";
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User find(String currentUserName) {
        return userRepository.findByUserName(currentUserName)
                .orElseThrow(
                        () -> new RuntimeException("UserName Not Found!!!!!!")
                );
    }


    // check if password matches in db
    @Override
    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }
}
