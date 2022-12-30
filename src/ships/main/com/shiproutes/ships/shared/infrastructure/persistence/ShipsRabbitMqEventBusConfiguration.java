package com.shiproutes.ships.shared.infrastructure.persistence;

import com.shiproutes.shared.infrastructure.bus.event.mysql.MySqlEventBus;
import com.shiproutes.shared.infrastructure.bus.event.rabbitmq.RabbitMqEventBus;
import com.shiproutes.shared.infrastructure.bus.event.rabbitmq.RabbitMqPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShipsRabbitMqEventBusConfiguration {
    private final RabbitMqPublisher publisher;
    private final MySqlEventBus failoverPublisher;

    public ShipsRabbitMqEventBusConfiguration(
        RabbitMqPublisher publisher,
        @Qualifier("shipsMysqlEventBus") MySqlEventBus failoverPublisher
    ) {
        this.publisher = publisher;
        this.failoverPublisher = failoverPublisher;
    }

    @Bean
    public RabbitMqEventBus shipsRabbitMqEventBus() {
        return new RabbitMqEventBus(publisher, failoverPublisher);
    }
}
