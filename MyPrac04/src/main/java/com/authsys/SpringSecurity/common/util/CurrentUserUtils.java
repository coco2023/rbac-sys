package com.authsys.SpringSecurity.common.util;

import com.authsys.SpringSecurity.entity.User;
import com.authsys.SpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component

public class CurrentUserUtils {

    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        return userService.find(getCurrentUserName());
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

}
