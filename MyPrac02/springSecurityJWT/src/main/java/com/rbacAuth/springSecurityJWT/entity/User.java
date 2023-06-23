package com.rbacAuth.springSecurityJWT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rbacAuth.springSecurityJWT.dto.UserRepresentation;
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
@Entity
@Builder
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String userName;

    private String password;

    private long telephone;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("user")
//    private List<UserRole> userRoles = new ArrayList<>();
//
//    public List<SimpleGrantedAuthority> getRoles() {
//        List<Role> roles = userRoles.stream()
//                .map(UserRole::getRole)
//                .collect(Collectors.toList());
//
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        roles.forEach(
//                role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName()))
//        );
//
//        return authorities;
//
//    }

    public UserRepresentation toUserRepresentation() {
        return UserRepresentation.builder()
                .userName(getUserName())
                .telephone(getTelephone())
                .build();
    }
}