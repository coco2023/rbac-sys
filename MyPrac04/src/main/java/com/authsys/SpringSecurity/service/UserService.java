package com.authsys.SpringSecurity.service;

import com.authsys.SpringSecurity.entity.Role;
import com.authsys.SpringSecurity.entity.User;
import com.authsys.SpringSecurity.entity.UserRole;
import com.authsys.SpringSecurity.enums.RoleType;
import com.authsys.SpringSecurity.model.UserRegisterRequest;
import com.authsys.SpringSecurity.repository.RoleRepository;
import com.authsys.SpringSecurity.repository.UserRepository;
import com.authsys.SpringSecurity.repository.UserRoleRepository;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Transactional(rollbackFor = Exception.class)
    public void save(UserRegisterRequest userRegisterRequest) {

        ensureUserNameNotExist(userRegisterRequest.getUserName());
        User user = userRegisterRequest.toUser();

        userRepository.save(user);

        Role studentRole = roleRepository.findByName(RoleType.USER.getName()).orElseThrow(() -> new RuntimeException("Role_NOT_FOUND"));
        Role managerRole = roleRepository.findByName(RoleType.MANAGER.getName()).orElseThrow(() -> new RuntimeException("Role_NOT_FOUND"));
        userRoleRepository.save(new UserRole(user, studentRole));
        userRoleRepository.save(new UserRole(user, managerRole));

    }

    private void ensureUserNameNotExist(String userName) {
        boolean exist = userRepository.findByUserName(userName).isPresent();
        if (exist) {
            throw new RuntimeException("UserName Already Exist!");
        }
    }

    public User find(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("USERNAME_NOT_FOUND"));

    }

    public boolean check(String currentPassword, String password) {
        return currentPassword.equals(password);
    }



}
