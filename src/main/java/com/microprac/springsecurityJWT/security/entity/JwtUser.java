package com.microprac.springsecurityJWT.security.entity;

import com.microprac.springsecurityJWT.system.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class JwtUser implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(User user) {
        id = user.getId();
        username = user.getUserName();
        password = user.getPassword();
        enabled = user.getEnabled() == null ? true : user.getEnabled();
        authorities = user.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
