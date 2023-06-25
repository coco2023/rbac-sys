package com.authsys.SpringSecurity.controller;

import com.authsys.SpringSecurity.model.UserRegisterRequest;
import com.authsys.SpringSecurity.model.UserRepresentation;
import com.authsys.SpringSecurity.model.UserUpdateRequest;
import com.authsys.SpringSecurity.service.UserService;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<Page<UserRepresentation>> getAllUser(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<UserRepresentation> allUser = userService.getAll(pageNum, pageSize);
        return ResponseEntity.ok().body(allUser);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        userService.update(userUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> deleteUserByUserName(@RequestParam("username") String username) {
        userService.delete(username);
        return ResponseEntity.ok().build();
    }


}
