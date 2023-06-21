package com.rbacAuth.springSecurityJWT.service;

import com.rbacAuth.springSecurityJWT.dto.UserRegisterRequest;
import com.rbacAuth.springSecurityJWT.dto.UserRepresentation;
import org.springframework.data.domain.Page;

public interface UserService {
    void save(UserRegisterRequest userRegisterRequest);

    Page<UserRepresentation> getAll(int pageNum, int pageSize);
}
