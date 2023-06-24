package com.authsys.SpringSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoginRequest {

    private String userName;

    private String password;

    private Boolean rememberMe;


}
