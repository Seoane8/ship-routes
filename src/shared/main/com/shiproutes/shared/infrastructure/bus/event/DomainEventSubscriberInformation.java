package com.shiproutes.shared.infrastructure.bus.event;

import com.shiproutes.shared.domain.Utils;
import com.shiproutes.shared.domain.bus.event.DomainEvent;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;

import java.util.List;

public final class DomainEventSubscriberInformation {
    private final Class<? extends DomainEventSubscriber> subscriberClass;
    private final List<Class<? extends DomainEvent>> subscribedEvents;

    public DomainEventSubscriberInformation(
        Class<? extends DomainEventSubscriber> subscriberClass,
        List<Class<? extends DomainEvent>> subscribedEvents
    ) {
        this.subscriberClass = subscriberClass;
        this.subscribedEvents = subscribedEvents;
    }

    public Class<? extends DomainEventSubscriber> subscriberClass() {
        return subscriberClass;
    }

    public String contextName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[2];
    }

    public String moduleName() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[3];
    }

    public String className() {
        String[] nameParts = subscriberClass.getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

    public List<Class<? extends DomainEvent>> subscribedEvents() {
        return subscribedEvents;
    }

    public String formatRabbitMqQueueName() {
        return String.format("shiproutes.%s.%s.%s", contextName(), moduleName(), Utils.toSnake(className()));
    }
}
