package com.microprac.springsecurityJWT.system.constant;

import lombok.Getter;

@Getter
public enum RoleType {

    USER("USER", "User"),

    TEMP_USER("TEMP_USER", "Temp_User"),

    MANAGER("MANAGER", "Manager"),

    ADMIN("ADMIN", "Admin");

    private final String name;
    private final String description;

    RoleType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

