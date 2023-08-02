package com.shiproutes.shared.infrastructure.auth;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("(" +
    "hasRole(T(com.shiproutes.shared.domain.UserRole).ADMIN) + " +
    "hasRole(T(com.shiproutes.shared.domain.UserRole).USER))")
public @interface AuthorizeAll {
}
