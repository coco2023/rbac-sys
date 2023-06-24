package com.rbacAuth.springSecurityJWT.service.impl;

import com.rbacAuth.springSecurityJWT.dto.UserRegisterRequest;
import com.rbacAuth.springSecurityJWT.entity.Role;
import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.entity.UserRole;
import com.rbacAuth.springSecurityJWT.enums.RoleType;
import com.rbacAuth.springSecurityJWT.repository.RoleRepository;
import com.rbacAuth.springSecurityJWT.repository.UserRepository;
import com.rbacAuth.springSecurityJWT.repository.UserRoleRepository;
import com.rbacAuth.springSecurityJWT.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<User> findAllUser() {

        return userRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(UserRegisterRequest userRegisterRequest, String roleType) {

        // check if user exists & passwords match
        checkUserRegisterInfo(userRegisterRequest);

        User user = userRegisterRequest.toUser();
        userRepository.save(user);

        Role role = roleRepository.findByRoleName(roleType)
                .orElseThrow(
                        () -> new RuntimeException("Role does not exist!")
                );

        UserRole userRole = new UserRole(user, role);
        userRoleRepository.save(userRole);

    }

    private void checkUserRegisterInfo(UserRegisterRequest userRegisterRequest) {
        checkUsernameUnique(userRegisterRequest.getUserName());
    }

    private void checkUsernameUnique(String userName) {
        boolean exist = userRepository.findByUserName(userName).isPresent();
        if (exist) {
            throw new RuntimeException("ERROR_User name has already existed!");
        }
    }


}
