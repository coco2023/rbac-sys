package com.rbacAuth.springSecurityJWT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "role")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleId;

    private String roleName;

    private String dataScope;

    private String description;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("role")
//    private List<UserRole> userRoles = new ArrayList<>();
//
//    public Role(String roleName, String dataScope, String description) {
//        this.roleName = roleName;
//        this.dataScope = dataScope;
//        this.description = description;
//    }

}