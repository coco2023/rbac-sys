package com.microprac.springsecurityJWT.security.service;

import com.microprac.springsecurityJWT.security.dto.LoginRequest;

public interface AuthService {
    String createToken(LoginRequest loginRequest);

    void removeToken();
}
