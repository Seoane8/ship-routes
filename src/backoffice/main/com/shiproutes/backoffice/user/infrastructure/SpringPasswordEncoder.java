package com.shiproutes.backoffice.user.infrastructure;

import com.shiproutes.backoffice.user.domain.PasswordEncoder;
import com.shiproutes.backoffice.user.domain.UserPassword;
import com.shiproutes.backoffice.user.domain.UserRawPassword;
import com.shiproutes.shared.domain.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class SpringPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder encoder;

    public SpringPasswordEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserPassword encode(UserRawPassword rawPassword) {
        return new UserPassword(encoder.encode(rawPassword.value()));
    }

    @Override
    public boolean matches(UserRawPassword rawPassword, UserPassword encodedPassword) {
        return encoder.matches(rawPassword.value(), encodedPassword.value());
    }
}
