package com.rbacAuth.springSecurityJWT.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserRepresentation {

    private String userName;

    private Date time;
}
