package com.rbacAuth.springSecurityJWT.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class LoginRequest {

    private String username;
    private String password;
    private Boolean rememberMe;

}
