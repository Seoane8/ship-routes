package com.shiproutes.apps.routes.backend.command;

import com.shiproutes.shared.infrastructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import com.shiproutes.shared.infrastructure.cli.ConsoleCommand;

public class ConsumeRabbitMqDomainEventsCommand extends ConsoleCommand {

    private final RabbitMqDomainEventsConsumer consumer;

    public ConsumeRabbitMqDomainEventsCommand(RabbitMqDomainEventsConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void execute(String[] queues) {
        consumer.consume(queues);
    }
}
