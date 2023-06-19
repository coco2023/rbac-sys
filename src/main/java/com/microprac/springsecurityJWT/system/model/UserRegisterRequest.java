package com.microprac.springsecurityJWT.system.model;

import com.microprac.springsecurityJWT.system.entity.User;
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
    private String fullName;

    public User toUser() {
        return User.builder()
                .fullName(getFullName())
                .userName(getUserName())
                .enabled(true)
                .build();
    }
}
