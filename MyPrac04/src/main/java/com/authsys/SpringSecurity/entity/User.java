package com.authsys.SpringSecurity.entity;

import com.authsys.SpringSecurity.model.UserRepresentation;
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

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<Role> roles = getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    private List<Role> getRoles() {
        return userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
    }

    public UserRepresentation toUserRepresentation() {
        return UserRepresentation.builder()
                .fullName(this.fullName)
                .userName(this.userName)
                .role(getRoles())
                .build();
    }

}
