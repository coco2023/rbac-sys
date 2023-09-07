package com.prac.ecomm.vo.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
