package com.shiproutes.apps.ships.backend.config;

import com.shiproutes.apps.ships.backend.middleware.BasicHttpAuthMiddleware;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShipServerConfiguration {
    private final CommandBus bus;

    public ShipServerConfiguration(CommandBus bus) {
        this.bus = bus;
    }

    @Bean
    public FilterRegistrationBean<BasicHttpAuthMiddleware> basicHttpAuthMiddleware() {
        FilterRegistrationBean<BasicHttpAuthMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new BasicHttpAuthMiddleware(bus));
        registrationBean.addUrlPatterns("/health-check");

        return registrationBean;
    }
}
