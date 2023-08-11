package com.shiproutes.backoffice.user;

import com.shiproutes.backoffice.user.domain.*;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserModuleUnitTestCase extends UnitTestCase {

    protected UserRepository repository;
    protected PasswordEncoder encoder;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        repository = mock(UserRepository.class);
        encoder = mock(PasswordEncoder.class);

        when(repository.search((Username) any())).thenReturn(Optional.empty());
        when(repository.exists(any(), any())).thenReturn(false);
        when(encoder.matches(any(), any())).thenReturn(false);
    }

    protected void shouldHaveSaved(User user) {
        verify(repository, atLeastOnce()).save(user);
    }

    protected void shouldExist(User user) {
        when(repository.search(user.username())).thenReturn(Optional.of(user));
    }

    protected void shouldExistUserWithSameUsernameOrEmail(User user) {
        when(repository.exists(user.username(), user.email())).thenReturn(true);
    }

    protected void shouldEncode(UserRawPassword rawPassword, UserPassword encodedPassword) {
        when(encoder.encode(rawPassword)).thenReturn(encodedPassword);
    }

    protected void shouldMatch(UserRawPassword rawPassword, UserPassword encodedPassword) {
        when(encoder.matches(rawPassword, encodedPassword)).thenReturn(true);
    }

}
