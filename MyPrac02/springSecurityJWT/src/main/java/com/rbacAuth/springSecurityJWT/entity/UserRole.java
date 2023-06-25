package com.rbacAuth.springSecurityJWT.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    private long userId;

    private long roleId;

//    @ManyToOne
//    @JoinColumn
//    private User user;
//
//    @ManyToOne
//    @JoinColumn
//    private Role role;
//
//    public UserRole(User user, Role role) {
//        this.user = user;
//        this.role = role;
//    }
//
//    public UserRole(long userId, long roleId, User user, Role role) {
//        this.userId = userId;
//        this.roleId = roleId;
//        this.user = user;
//        this.role = role;
//    }
//
    public UserRole(long userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}