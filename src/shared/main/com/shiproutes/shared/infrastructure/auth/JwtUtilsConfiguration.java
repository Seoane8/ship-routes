package com.shiproutes.shared.infrastructure.auth;

import com.shiproutes.shared.infrastructure.config.Parameter;
import com.shiproutes.shared.infrastructure.config.ParameterNotExist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtUtilsConfiguration {

    private final Parameter config;

    public JwtUtilsConfiguration(Parameter config) {
        this.config = config;
    }

    @Bean
    public JwtUtils jwtUtils() throws ParameterNotExist {
        return new JwtUtils(
            config.get("JWT_SECRET"),
            config.getInt("JWT_EXPIRATION_TIME")
        );
    }
}
