package com.authsys.SpringSecurity.repository;

import com.authsys.SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author shuang.kou
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String username);

    // TODO:  deleteByUserName(), existsByUserName()
}
