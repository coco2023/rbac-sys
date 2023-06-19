package com.microprac.springsecurityJWT.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microprac.springsecurityJWT.system.model.UserRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

    public Collection<SimpleGrantedAuthority> getRoles() {
        List<Role> roles = userRoles.stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(
                role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()))
        );

        return authorities;
    }

    public UserRepresentation toUserRepresentation() {
        return UserRepresentation.builder()
                .fullName(getFullName())
                .userName(getUserName())
                .build();
    }
}
