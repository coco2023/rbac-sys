package com.rbacAuth.springSecurityJWT.service;

import com.rbacAuth.springSecurityJWT.constant.UserRoleEnum;
import com.rbacAuth.springSecurityJWT.dto.UserRegisterRequest;
import com.rbacAuth.springSecurityJWT.dto.UserRepresentation;
import org.springframework.data.domain.Page;

public interface UserService {
    void saveNormalUser(UserRegisterRequest userRegisterRequest);

    Page<UserRepresentation> getAll(int pageNum, int pageSize);

    void save(UserRegisterRequest userRegisterRequest, UserRoleEnum userRole);

}
