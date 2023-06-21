package com.rbacAuth.springSecurityJWT.entity;

import com.rbacAuth.springSecurityJWT.dto.UserRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String userName;

    private String password;

    private long telephone;

    public UserRepresentation toUserRepresentation() {
        return UserRepresentation.builder()
                .userName(getUserName())
                .telephone(getTelephone())
                .build();
    }
}