package com.rbacAuth.springSecurityJWT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String password;

    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

    public List<SimpleGrantedAuthority> getRoles() {
        List<Role> roles = userRoles.stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(
                role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName()))
        );

        return authorities;
    }

}
