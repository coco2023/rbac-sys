package com.rbacAuth.springSecurityJWT.util;

import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component

public class CurrentUserUtils {

    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        return userService.findByUserName(getCurrentUserName()).get();
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }


}
