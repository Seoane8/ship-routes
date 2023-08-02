package com.shiproutes.backoffice.user.application.authenticate;

import com.shiproutes.backoffice.user.UserModuleUnitTestCase;
import com.shiproutes.backoffice.user.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserAuthenticatorShould extends UserModuleUnitTestCase {

    UserAuthenticator authenticator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        authenticator = new UserAuthenticator(repository, encoder);
    }

    @Test
    void authenticate_user_with_valid_credentials() {
        User user = UserMother.random();
        UserRawPassword password = UserRawPasswordMother.random();
        shouldExist(user);
        shouldMatch(password, user.password());

        UserResponse response = authenticator.authenticate(user.username(), password);

        assertEquals(UserResponse.from(user), response);
    }

    @Test
    void fail_when_user_not_exist() {
        assertThrows(InvalidCredentials.class, () -> {
            User user = UserMother.random();
            UserRawPassword password = UserRawPasswordMother.random();
            shouldMatch(password, user.password());

            authenticator.authenticate(user.username(), password);
        });
    }

    @Test
    void fail_when_password_does_not_match() {
        assertThrows(InvalidCredentials.class, () -> {
            User user = UserMother.random();
            UserRawPassword password = UserRawPasswordMother.random();
            shouldExist(user);

            authenticator.authenticate(user.username(), password);
        });
    }
}
