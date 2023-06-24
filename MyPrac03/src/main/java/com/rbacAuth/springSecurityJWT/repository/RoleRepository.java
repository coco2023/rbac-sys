package com.rbacAuth.springSecurityJWT.repository;

import com.rbacAuth.springSecurityJWT.entity.Role;
import com.rbacAuth.springSecurityJWT.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
