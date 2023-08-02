package com.shiproutes.backoffice.shared.infrastructure.events;

import com.shiproutes.shared.infrastructure.bus.event.mysql.MySqlEventBus;
import com.shiproutes.shared.infrastructure.bus.event.rabbitmq.RabbitMqEventBus;
import com.shiproutes.shared.infrastructure.bus.event.rabbitmq.RabbitMqPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class IngestRabbitMqEventBusConfiguration {
    private final RabbitMqPublisher publisher;
    private final MySqlEventBus failoverPublisher;

    public IngestRabbitMqEventBusConfiguration(
        RabbitMqPublisher publisher,
        @Qualifier("ingestMysqlEventBus") MySqlEventBus failoverPublisher
    ) {
        this.publisher = publisher;
        this.failoverPublisher = failoverPublisher;
    }

    @Bean
    @Primary
    public RabbitMqEventBus ingestRabbitMqEventBus() {
        return new RabbitMqEventBus(publisher, failoverPublisher);
    }
}
