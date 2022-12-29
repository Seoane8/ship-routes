package com.shiproutes.ship.auth.infrastructure.persistence;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.ship.auth.domain.AuthPassword;
import com.shiproutes.ship.auth.domain.AuthRepository;
import com.shiproutes.ship.auth.domain.AuthUser;
import com.shiproutes.ship.auth.domain.AuthUsername;

import java.util.HashMap;
import java.util.Optional;

@Service
public final class InMemoryAuthRepository implements AuthRepository {
    private final HashMap<AuthUsername, AuthPassword> users = new HashMap<AuthUsername, AuthPassword>() {{
        put(new AuthUsername("javi"), new AuthPassword("barbitas"));
        put(new AuthUsername("rafa"), new AuthPassword("pelazo"));
    }};

    @Override
    public Optional<AuthUser> search(AuthUsername username) {
        return users.containsKey(username)
            ? Optional.of(new AuthUser(username, users.get(username)))
            : Optional.empty();
    }
}
