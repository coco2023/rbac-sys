package com.microprac.springsecurityJWT.system.service;

import com.microprac.springsecurityJWT.system.entity.User;
import com.microprac.springsecurityJWT.system.model.UserRegisterRequest;
import com.microprac.springsecurityJWT.system.model.UserRepresentation;
import org.springframework.data.domain.Page;

public interface UserService {
    User find(String currentUserName);

    boolean check(String password, String password1);

    void save(UserRegisterRequest userRegisterRequest);

    Page<UserRepresentation> getAll(int pageNum, int pageSize);
}
