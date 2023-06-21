package com.rbacAuth.springSecurityJWT.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

/**
 * demo prac
 */
public class UserCollect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCollectId;

    private Long userId;

    private Long spuId;

    private String spuName;

    private LocalDateTime createTime;

}
