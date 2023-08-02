package com.shiproutes.shared.infrastructure.auth;

import com.shiproutes.shared.domain.UserRole;

import java.util.Objects;

public class JwtInfo {

    private final String id;
    private final String username;
    private final String email;
    private final String role;

    public JwtInfo(String id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public static JwtInfo unknown() {
        return new JwtInfo(
            "unknown",
            "unknown",
            "unknown",
            UserRole.USER.name()
        );
    }

    public String id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String email() {
        return email;
    }

    public String role() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JwtInfo)) return false;
        JwtInfo jwtInfo = (JwtInfo) o;
        return Objects.equals(id, jwtInfo.id) && Objects.equals(username, jwtInfo.username)
            && Objects.equals(email, jwtInfo.email) && Objects.equals(role, jwtInfo.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, role);
    }
}
