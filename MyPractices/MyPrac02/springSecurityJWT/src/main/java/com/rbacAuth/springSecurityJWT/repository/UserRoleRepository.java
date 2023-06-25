package com.rbacAuth.springSecurityJWT.repository;

import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserId(long userId);

    List<Long> findRoleIdByUserId(long userId);
}
