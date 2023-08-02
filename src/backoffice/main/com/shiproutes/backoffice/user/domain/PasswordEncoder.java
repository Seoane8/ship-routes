package com.shiproutes.backoffice.user.domain;

public interface PasswordEncoder {

    UserPassword encode(UserRawPassword rawPassword);

    boolean matches(UserRawPassword rawPassword, UserPassword encodedPassword);

}
