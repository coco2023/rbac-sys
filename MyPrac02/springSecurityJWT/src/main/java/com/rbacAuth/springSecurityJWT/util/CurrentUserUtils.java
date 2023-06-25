package com.rbacAuth.springSecurityJWT.util;

import com.rbacAuth.springSecurityJWT.entity.User;
import com.rbacAuth.springSecurityJWT.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * get the user sending current request
 */

@Component
@Log4j2

public class CurrentUserUtils {

    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        User user = userService.findUserByName(getCurrentUserName()).get();
        log.info("this is User: " + user);

        return user;
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication info: " + authentication.getPrincipal() + ", " + authentication.getDetails() + " ," + authentication);
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
