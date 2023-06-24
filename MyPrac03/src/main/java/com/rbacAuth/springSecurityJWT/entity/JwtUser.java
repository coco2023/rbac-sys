package com.rbacAuth.springSecurityJWT.entity;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ToString

public class JwtUser implements UserDetails {

    private Long id;

    private String userName;

    private String password;

    private Boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * create JwtUser through User
     * @param user
     */
    public JwtUser(User user) {
        id = user.getUserId();
        userName = user.getUserName();
        password = user.getPassword();
        enabled = user.getEnabled() == null ? true : user.getEnabled();
        authorities = user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
