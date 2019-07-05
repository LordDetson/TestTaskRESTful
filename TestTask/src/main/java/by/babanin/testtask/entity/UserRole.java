package by.babanin.testtask.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMINISTRATOR, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
