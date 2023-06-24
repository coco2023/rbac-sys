package com.rbacAuth.springSecurityJWT.controller;

import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Log4j2

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {

        List<User> users = userService.findAllUser();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
