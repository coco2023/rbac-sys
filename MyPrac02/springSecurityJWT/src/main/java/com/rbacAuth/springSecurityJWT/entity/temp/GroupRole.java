package com.rbacAuth.springSecurityJWT.entity.temp;

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

public class GroupRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long groupRoleId;

    private long roleId;

    private long groupId;

}