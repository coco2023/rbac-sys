package com.rbacAuth.springSecurityJWT.controller;

import com.rbacAuth.springSecurityJWT.dto.UserRepresentation;
import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Log4j2

public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public ResponseEntity<List<User>> getAllUser() {
//        List<User> users = userService.findAllUser();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('USER','ADMIN','CUSTOMER')")
    public ResponseEntity<Page<UserRepresentation>> getAllUser(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        log.info("checking all users info...");
        Page<UserRepresentation> allUser = userService.getAll(pageNum, pageSize);
        return ResponseEntity.ok().body(allUser);
    }


}
