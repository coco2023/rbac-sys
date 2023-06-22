package com.rbacAuth.springSecurityJWT.service.impl;

import com.rbacAuth.springSecurityJWT.constant.UserRoleEnum;
import com.rbacAuth.springSecurityJWT.dto.UserRegisterRequest;
import com.rbacAuth.springSecurityJWT.dto.UserRepresentation;
import com.rbacAuth.springSecurityJWT.entity.Role;
import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.entity.UserRole;
import com.rbacAuth.springSecurityJWT.repository.RoleRepository;
import com.rbacAuth.springSecurityJWT.repository.UserRepository;
import com.rbacAuth.springSecurityJWT.repository.UserRoleRepository;
import com.rbacAuth.springSecurityJWT.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Log4j2

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveNormalUser(UserRegisterRequest userRegisterRequest) {
        checkUserRegisterInfo(userRegisterRequest);

        // encode password & save user info
        User user = userRegisterRequest.toUser();
//        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        user.setPassword(userRegisterRequest.getPassword());
        userRepository.save(user);

        // set ROLE to user: USER
        Role role_normalUser = (Role) roleRepository.findByRoleName("USER")
                .orElseThrow(
                        () -> new RuntimeException("USER_ROLE_NOT_FOUND!")
                );
        Role role_manager = (Role) roleRepository.findByRoleName("MANAGER")
                .orElseThrow(
                        () -> new RuntimeException("MANAGER_ROLE_NOT_FOUND!")
                );

        log.info("this is user info: " + user);
        userRoleRepository.save(new com.rbacAuth.springSecurityJWT.entity.UserRole(user.getUserId(), role_normalUser.getRoleId()));
        userRoleRepository.save(new com.rbacAuth.springSecurityJWT.entity.UserRole(user.getUserId(), role_manager.getRoleId()));

    }

    @Override
    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize))
                .map(User::toUserRepresentation);
    }

    @Override
    public void save(UserRegisterRequest userRegisterRequest, UserRoleEnum userRoleEnum) {

        checkUserRegisterInfo(userRegisterRequest);

        // encode password & save user info
        User user = userRegisterRequest.toUser();
//        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        user.setPassword(userRegisterRequest.getPassword());
        userRepository.save(user);

        // set ROLE to user: USER
        Role role = (Role) roleRepository.findByRoleName(String.valueOf(userRoleEnum))
                .orElseThrow(
                        () -> new RuntimeException("USER_ROLE_NOT_FOUND!")
                );

        log.info("this is user info: " + user);
        userRoleRepository.save(new UserRole(user.getUserId(), role.getRoleId()));

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
