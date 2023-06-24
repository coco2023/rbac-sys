package com.authsys.SpringSecurity.model;

import com.authsys.SpringSecurity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String fullName;

    public User toUser() {
        return User.builder().fullName(this.getFullName())
                .userName(this.getUserName())
                .password(this.getPassword())
                .enabled(true).build();
    }
}
