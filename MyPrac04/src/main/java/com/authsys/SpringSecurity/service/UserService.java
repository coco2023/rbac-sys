package com.authsys.SpringSecurity.service;

import com.authsys.SpringSecurity.entity.Role;
import com.authsys.SpringSecurity.entity.User;
import com.authsys.SpringSecurity.entity.UserRole;
import com.authsys.SpringSecurity.enums.RoleType;
import com.authsys.SpringSecurity.external.ProductFeignService;
import com.authsys.SpringSecurity.external.response.ProductResponse;
import com.authsys.SpringSecurity.model.UserRegisterRequest;
import com.authsys.SpringSecurity.model.UserRepresentation;
import com.authsys.SpringSecurity.model.UserUpdateRequest;
import com.authsys.SpringSecurity.repository.RoleRepository;
import com.authsys.SpringSecurity.repository.UserRepository;
import com.authsys.SpringSecurity.repository.UserRoleRepository;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ProductFeignService productFeignService;

    @Transactional(rollbackFor = Exception.class)
    public void saveUserByRole(UserRegisterRequest userRegisterRequest, String roleType) {
        ensureUserNameNotExist(userRegisterRequest.getUserName());
        User user = userRegisterRequest.toUser();

        userRepository.save(user);

        Role role = roleRepository.findByName(roleType).orElseThrow(() -> new RuntimeException("Role_NOT_FOUND"));
        userRoleRepository.save(new UserRole(user, role));
    }

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

    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize)).map(User::toUserRepresentation);
    }


    public void update(UserUpdateRequest userUpdateRequest) {
        User user = find(userUpdateRequest.getUserName());

        if (Objects.nonNull(userUpdateRequest.getFullName())) {
            user.setFullName(userUpdateRequest.getFullName());
        }
        if (Objects.nonNull(userUpdateRequest.getPassword())) {
            user.setPassword(userUpdateRequest.getPassword());
        }
        if (Objects.nonNull(userUpdateRequest.getEnabled())) {
            user.setEnabled(userUpdateRequest.getEnabled());
        }
        userRepository.save(user);
    }

    public void delete(String userName) {
        if (!userRepository.existsByUserName(userName)) {
            throw new RuntimeException("USER_NAME_NOT_FOUND");
        }
        userRepository.deleteByUserName(userName);
    }

    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> allProducts = productFeignService.getAllProduct().getBody();
        return allProducts;
    }
}
