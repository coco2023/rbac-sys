package com.microprac.springsecurityJWT.security.controller;

import com.microprac.springsecurityJWT.security.common.constant.SecurityConstants;
import com.microprac.springsecurityJWT.security.common.util.CurrentUserUtils;
import com.microprac.springsecurityJWT.security.dto.LoginRequest;
import com.microprac.springsecurityJWT.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
// The `onConstructor` attribute specifies that the `@Autowired` annotation should be added to the generated constructor
// `@RequiredArgsConstructor` generates a constructor that includes all the fields marked with final
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2

public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.createToken(loginRequest);
        log.info("token: " + token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConstants.TOKEN_HEADER, token);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.removeToken();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
