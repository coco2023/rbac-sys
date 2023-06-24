package com.authsys.SpringSecurity.enums;

import lombok.Getter;

@Getter

public enum RoleType {

    USER("USER", "system users"),
    TEMP_USER("TEMP_USER", "temp users"),
    MANAGER("MANAGER", "managers"),
    ADMIN("ADMIN", "Admin");
    private final String name;
    private final String description;

    RoleType(java.lang.String name, java.lang.String description) {
        this.name = name;
        this.description = description;
    }

}
