package com.shiproutes.backoffice.user;

import com.shiproutes.backoffice.user.domain.*;
import com.shiproutes.shared.infrastructure.UnitTestCase;

import static org.mockito.Mockito.*;

public class UserModuleUnitTestCase extends UnitTestCase {

    protected UserRepository repository;
    protected PasswordEncoder encoder;

    protected void setUp() {
        super.setUp();
        repository = mock(UserRepository.class);
        encoder = mock(PasswordEncoder.class);
    }

    protected void shouldHaveSaved(User user) {
        verify(repository, atLeastOnce()).save(user);
    }

    protected void shouldEncode(UserRawPassword rawPassword, UserPassword encodedPassword) {
        when(encoder.encode(rawPassword)).thenReturn(encodedPassword);
    }

    protected void shouldExistUserWithSameUsernameOrEmail(User user) {
        when(repository.exists(user.username(), user.email())).thenReturn(true);
    }

}
