package com.rbacAuth.springSecurityJWT.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRepresentation {

    private String userName;

    private Long telephone;
}
