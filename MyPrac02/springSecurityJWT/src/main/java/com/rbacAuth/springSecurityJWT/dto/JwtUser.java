package com.rbacAuth.springSecurityJWT.dto;

import com.rbacAuth.springSecurityJWT.entity.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class JwtUser {

    private Long id;
    private String username;
    private String password;
    private Boolean enabled;

    //TODO: did not check how to get userRole form table
//    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(User user) {
        this.id = user.getUserId();
        this.username = user.getUserName();
        this.enabled = true;
//        authorities = user.getRoles();
    }
}
