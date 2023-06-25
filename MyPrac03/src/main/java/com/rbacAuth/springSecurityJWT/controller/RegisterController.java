package com.rbacAuth.springSecurityJWT.controller;

import com.rbacAuth.springSecurityJWT.dto.UserRegisterRequest;
import com.rbacAuth.springSecurityJWT.enums.RoleType;
import com.rbacAuth.springSecurityJWT.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@Log4j2

public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest, "USER");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer")
    public ResponseEntity<Void> customerRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest, RoleType.CUSTOMER.toString());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/supplier")
    public ResponseEntity<Void> supplierRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest, RoleType.SUPPLIER.toString());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> adminRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest, RoleType.ADMIN.toString());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test")
    public ResponseEntity<Void> testRegister(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest, RoleType.TEST.toString());
        return ResponseEntity.ok().build();
    }


}
