package com.authsys.SpringSecurity.enums;

import lombok.Getter;

@Getter

public enum RoleType {

    USER("USER", "system users"),

    TESTER("TESTER", "sys testers"),

    MANAGER("MANAGER", "managers"),

    ADMIN("ADMIN", "Admin"),

    CUSTOMER("CUSTOMER", "customer"),

    SUPPLIER("SUPPLIER", "supplier");


    private final String name;
    private final String description;

    RoleType(java.lang.String name, java.lang.String description) {
        this.name = name;
        this.description = description;
    }

}
