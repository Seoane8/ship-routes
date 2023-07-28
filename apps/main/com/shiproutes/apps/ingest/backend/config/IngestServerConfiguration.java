package com.shiproutes.apps.ingest.backend.config;

import com.shiproutes.shared.infrastructure.spring.ApiExceptionMiddleware;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class IngestServerConfiguration {
    private final RequestMappingHandlerMapping mapping;

    public IngestServerConfiguration(RequestMappingHandlerMapping mapping) {
        this.mapping = mapping;
    }

    @Bean
    public FilterRegistrationBean<ApiExceptionMiddleware> apiExceptionMiddleware() {
        FilterRegistrationBean<ApiExceptionMiddleware> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ApiExceptionMiddleware(mapping));

        return registrationBean;
    }
}
