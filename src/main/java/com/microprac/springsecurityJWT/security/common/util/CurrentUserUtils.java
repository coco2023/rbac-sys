package com.microprac.springsecurityJWT.security.common.util;

import com.microprac.springsecurityJWT.system.entity.User;
import com.microprac.springsecurityJWT.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class CurrentUserUtils {

    private final UserService userService;

    public User getCurrentUser() {
        return userService.find(getCurrentUserName());
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null ) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }


}
