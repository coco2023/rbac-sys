package com.rbacAuth.springSecurityJWT.repository;

import com.rbacAuth.springSecurityJWT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<Object> findByUserName(String userName);
}
