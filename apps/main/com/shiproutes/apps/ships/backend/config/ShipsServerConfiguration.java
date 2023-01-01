package com.shiproutes.apps.ships.backend.config;

import com.shiproutes.apps.ships.backend.middleware.BasicHttpAuthMiddleware;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.infrastructure.spring.ApiExceptionMiddleware;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class ShipsServerConfiguration {
    private final CommandBus bus;
    private final RequestMappingHandlerMapping mapping;

    public ShipsServerConfiguration(CommandBus bus, RequestMappingHandlerMapping mapping) {
        this.bus = bus;
        this.mapping = mapping;
    }

    @Bean
    public FilterRegistrationBean<BasicHttpAuthMiddleware> basicHttpAuthMiddleware() {
        FilterRegistrationBean<BasicHttpAuthMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new BasicHttpAuthMiddleware(bus));
        registrationBean.addUrlPatterns("/health-check");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ApiExceptionMiddleware> apiExceptionMiddleware() {
        FilterRegistrationBean<ApiExceptionMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ApiExceptionMiddleware(mapping));

        return registrationBean;
    }
}
