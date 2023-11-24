package com.example.furniture.enums;

import org.springframework.security.core.GrantedAuthority;

public enum JobTitle implements GrantedAuthority {
    ROLE_USER, ROLE_SELLER, ROLE_MANAGER, ROLE_ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}