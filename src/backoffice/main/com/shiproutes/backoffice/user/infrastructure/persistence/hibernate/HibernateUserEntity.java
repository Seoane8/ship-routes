package com.shiproutes.backoffice.user.infrastructure.persistence.hibernate;

import com.shiproutes.backoffice.user.domain.*;
import com.shiproutes.shared.domain.UserRole;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "user")
@Table(name = "users")
public final class HibernateUserEntity {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;

    public HibernateUserEntity() {
    }

    public HibernateUserEntity(User user) {
        this.id = user.id().value();
        this.username = user.username().value();
        this.password = user.password().value();
        this.email = user.email().value();
        this.role = user.role().name();
    }

    public User toEntity() {
        return new User(
            new UserId(id),
            new Username(username),
            new UserPassword(password),
            new UserEmail(email),
            UserRole.valueOf(role)
        );
    }
}
