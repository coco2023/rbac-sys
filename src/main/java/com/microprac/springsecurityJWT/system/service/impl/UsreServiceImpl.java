package com.microprac.springsecurityJWT.system.service.impl;

import com.google.common.collect.ImmutableMap;
import com.microprac.springsecurityJWT.system.constant.RoleType;
import com.microprac.springsecurityJWT.system.entity.User;
import com.microprac.springsecurityJWT.system.entity.Role;
import com.microprac.springsecurityJWT.system.entity.UserRole;
import com.microprac.springsecurityJWT.system.exception.UserNameAlreadyExistException;
import com.microprac.springsecurityJWT.system.model.UserRepresentation;
import com.microprac.springsecurityJWT.system.repository.RoleRepository;
import com.microprac.springsecurityJWT.system.repository.UserRepository;
import com.microprac.springsecurityJWT.system.repository.UserRoleRepository;
import com.microprac.springsecurityJWT.system.model.UserRegisterRequest;
import com.microprac.springsecurityJWT.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class UsreServiceImpl implements UserService {

    private static final String USERNAME = "username:";

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private UserRoleRepository userRoleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User find(String currentUserName) {
        return userRepository.findByUserName(currentUserName)
                .orElseThrow(
                        () -> new RuntimeException("UserName Not Found!!!!!!")
                );
    }


    // check if password matches in db
    @Override
    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    // save registered user
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserRegisterRequest userRegisterRequest) {
        ensureUserNameNotExist(userRegisterRequest.getUserName());

        User user = userRegisterRequest.toUser();
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        userRepository.save(user);

        // set ROLE to user: USER & MANAGER

        Role userRole = (Role) roleRepository.findByName(RoleType.USER.getName())
                .orElseThrow(
                        () -> new RuntimeException("ROLE_NOT_FOUND!")
                );

        Role managerRole = (Role) roleRepository.findByName(RoleType.MANAGER.getName())
                .orElseThrow(
                        () -> new RuntimeException("ROLE_NOT_FOUND!")
                );

        userRoleRepository.save(new UserRole(user, userRole));
        userRoleRepository.save(new UserRole(user, managerRole));

    }

    @Override
    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize))
                .map(User::toUserRepresentation);
    }

    private void ensureUserNameNotExist(String userName) {
        boolean exist = userRepository.findByUserName(userName).isPresent();
        if (exist) {
            throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME, userName));
        }
    }
}
