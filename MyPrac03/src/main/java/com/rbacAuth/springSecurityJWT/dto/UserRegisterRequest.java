package com.rbacAuth.springSecurityJWT.dto;

import com.rbacAuth.springSecurityJWT.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserRegisterRequest {

    private String userName;

    private String password;

    public User toUser() {
        return User.builder()
                .userName(getUserName())
                .password(getPassword())
                .enabled(true)
                .build();
    }

}
