package com.shiproutes.routes.shared.infrastructure.events;

import com.shiproutes.shared.infrastructure.bus.event.DomainEventsInformation;
import com.shiproutes.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import com.shiproutes.shared.infrastructure.bus.event.mysql.MySqlEventBus;
import com.shiproutes.shared.infrastructure.bus.event.spring.SpringApplicationEventBus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesMySqlEventBusConfiguration {
    private final SessionFactory sessionFactory;
    private final DomainEventsInformation domainEventsInformation;
    private final SpringApplicationEventBus bus;

    public RoutesMySqlEventBusConfiguration(
        @Qualifier("routes-session_factory") SessionFactory sessionFactory,
        DomainEventsInformation domainEventsInformation,
        SpringApplicationEventBus bus
    ) {
        this.sessionFactory = sessionFactory;
        this.domainEventsInformation = domainEventsInformation;
        this.bus = bus;
    }

    @Bean
    public MySqlEventBus routesMysqlEventBus() {
        return new MySqlEventBus(sessionFactory);
    }

    @Bean
    public MySqlDomainEventsConsumer routesMySqlDomainEventsConsumer() {
        return new MySqlDomainEventsConsumer(sessionFactory, domainEventsInformation, bus);
    }
}
