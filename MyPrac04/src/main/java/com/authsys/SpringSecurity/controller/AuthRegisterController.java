package com.authsys.SpringSecurity.controller;

import com.authsys.SpringSecurity.enums.RoleType;
import com.authsys.SpringSecurity.model.UserRegisterRequest;
import com.authsys.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/register")
public class AuthRegisterController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Void> normalRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.saveUserByRole(userRegisterRequest, RoleType.USER.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer")
    public ResponseEntity<Void> customerRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.saveUserByRole(userRegisterRequest, RoleType.CUSTOMER.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/supplier")
    public ResponseEntity<Void> supplierRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.saveUserByRole(userRegisterRequest, RoleType.SUPPLIER.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> adminRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.saveUserByRole(userRegisterRequest, RoleType.ADMIN.getName());
        return ResponseEntity.ok().build();
    }


}
