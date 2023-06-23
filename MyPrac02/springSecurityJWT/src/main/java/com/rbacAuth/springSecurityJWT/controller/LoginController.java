package com.rbacAuth.springSecurityJWT.controller;

import com.rbacAuth.springSecurityJWT.constant.SecurityConstants;
import com.rbacAuth.springSecurityJWT.dto.LoginRequest;
import com.rbacAuth.springSecurityJWT.service.AuthService;
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
@RequestMapping("/api/users")
@Log4j2

public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> normalLogin(@RequestBody LoginRequest loginRequest) {

        String token = authService.createToken(loginRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConstants.TOKEN_HEADER, token);

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.removeToken();
        log.info("Logout Success!");

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
