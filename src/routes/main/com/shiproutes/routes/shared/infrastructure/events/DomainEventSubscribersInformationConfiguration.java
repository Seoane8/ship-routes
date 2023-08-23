package com.shiproutes.routes.shared.infrastructure.events;

import com.shiproutes.shared.infrastructure.bus.event.DomainEventSubscribersInformation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainEventSubscribersInformationConfiguration {

    @Bean
    public DomainEventSubscribersInformation domainEventSubscribersInformation() {
        return new DomainEventSubscribersInformation("com.shiproutes.routes");
    }
}
