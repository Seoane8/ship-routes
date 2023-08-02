package com.shiproutes.backoffice.user.application.create;

import com.shiproutes.backoffice.user.UserModuleUnitTestCase;
import com.shiproutes.backoffice.user.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserCreatorShould extends UserModuleUnitTestCase {

    UserCreator creator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        creator = new UserCreator(repository, encoder);
    }

    @Test
    void create_a_new_user() {
        User user = UserMother.randomUser();
        UserRawPassword rawPassword = UserRawPasswordMother.random();
        shouldEncode(rawPassword, user.password());

        creator.create(user.id(), user.username(), rawPassword, user.email());

        shouldHaveSaved(user);
    }

    @Test
    void fail_when_exists_user_with_same_username_or_email() {
        assertThrows(UserAlreadyExists.class, () -> {
            User user = UserMother.random();
            UserRawPassword rawPassword = UserRawPasswordMother.random();
            shouldEncode(rawPassword, user.password());
            shouldExistUserWithSameUsernameOrEmail(user);

            creator.create(user.id(), user.username(), rawPassword, user.email());
        });
    }
}
