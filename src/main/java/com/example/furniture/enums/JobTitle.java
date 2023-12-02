package com.example.furniture.enums;

import org.springframework.security.core.GrantedAuthority;

public enum JobTitle implements GrantedAuthority {
    ROLE_USER("Пользователь"),
    ROLE_SELLER("Продавец"),
    ROLE_MANAGER("Менеджер"),
    ROLE_ADMIN("Админ");

    final String translate;

    JobTitle(String translate) {
        this.translate = translate;
    }

    public String getTranslate(){
        return translate;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}