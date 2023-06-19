package com.microprac.springsecurityJWT.system.controller;

import com.microprac.springsecurityJWT.system.model.UserRegisterRequest;
import com.microprac.springsecurityJWT.system.model.UserRepresentation;
import com.microprac.springsecurityJWT.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/users")

public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        userService.save(userRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<Page<UserRepresentation>> getAllUser(
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<UserRepresentation> allUser = userService.getAll(pageNum, pageSize);
        return ResponseEntity.ok().body(allUser);
    }
}
