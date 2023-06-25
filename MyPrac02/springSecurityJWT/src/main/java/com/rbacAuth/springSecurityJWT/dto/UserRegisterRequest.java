package com.rbacAuth.springSecurityJWT.dto;

import com.rbacAuth.springSecurityJWT.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class UserRegisterRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private Long telephone;

    public User toUser() {
        return User.builder()
                .userName(getUserName())
                .password(getPassword())
                .telephone(getTelephone())
                .build();
    }
}
