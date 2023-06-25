package com.rbacAuth.springSecurityJWT.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UserLoginRequest {

    private String userName;

    private String password;

    private Boolean rememberMe;


}
