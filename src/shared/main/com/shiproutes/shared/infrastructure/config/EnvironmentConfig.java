package com.shiproutes.shared.infrastructure.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Arrays;
import java.util.List;

@Configuration
public class EnvironmentConfig {
    ResourceLoader resourceLoader;
    Environment environment;

    public EnvironmentConfig(ResourceLoader resourceLoader, Environment environment) {
        this.resourceLoader = resourceLoader;
        this.environment = environment;
    }

    @Bean
    public Dotenv dotenv() {

        return Dotenv
            .configure()
            .directory("/")
            .filename(getFilename())
            .load();
    }

    private String getFilename() {
        List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
        if (activeProfiles.contains("test") && resourceLoader.getResource("classpath:/.env.test").exists()) {
            return ".env.test";
        }
        if (resourceLoader.getResource("classpath:/.env.local").exists()) {
            return ".env.local";
        }
        return ".env";
    }
}
