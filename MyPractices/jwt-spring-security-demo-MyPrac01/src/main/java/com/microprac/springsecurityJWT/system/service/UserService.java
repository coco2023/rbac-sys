package com.microprac.springsecurityJWT.system.service;

import com.microprac.springsecurityJWT.system.entity.User;

public interface UserService {
    User find(String currentUserName);

    boolean check(String password, String password1);
}
