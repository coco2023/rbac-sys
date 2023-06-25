package com.rbacAuth.springSecurityJWT.controller;

import com.rbacAuth.springSecurityJWT.constant.UserRoleEnum;
import com.rbacAuth.springSecurityJWT.dto.UserRegisterRequest;
import com.rbacAuth.springSecurityJWT.dto.UserRepresentation;
import com.rbacAuth.springSecurityJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    // normal user register
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.saveNormalUser(userRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customers/register")
    public ResponseEntity<Void> customerRegister(
            @RequestBody UserRegisterRequest userRegisterRequest
    ) {
        userService.save(userRegisterRequest, UserRoleEnum.CUSTOMER);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/suppliers/register")
    public ResponseEntity<Void> supplierRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest, UserRoleEnum.SUPPLIER);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/register")
    public ResponseEntity<Void> adminRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest, UserRoleEnum.ADMIN);
        return ResponseEntity.ok().build();
    }

//    @GetMapping
////    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Page<UserRepresentation>> getAllUser(
//            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
//            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
//    ) {
//        Page<UserRepresentation> allUser = userService.getAll(pageNum, pageSize);
//        return ResponseEntity.ok().body(allUser);
//    }
}
