package com.prac.ecomm.vo.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class LoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
