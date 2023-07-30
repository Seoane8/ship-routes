package com.shiproutes.shared.infrastructure.bus.event;

import com.shiproutes.shared.domain.bus.event.DomainEvent;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.bus.query.Query;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.util.*;

public final class DomainEventSubscribersInformation {
    HashMap<String, DomainEventSubscriberInformation> information;

    public DomainEventSubscribersInformation(String packageName) {
        this(scanDomainEventSubscribers(packageName));
    }

    public DomainEventSubscribersInformation(HashMap<String, DomainEventSubscriberInformation> information) {
        this.information = information;
    }

    private static HashMap<String, DomainEventSubscriberInformation> scanDomainEventSubscribers(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends DomainEventSubscriber>> subscribers = reflections.getSubTypesOf(DomainEventSubscriber.class);

        HashMap<String, DomainEventSubscriberInformation> subscribersInformation = new HashMap<>();

        for (Class<? extends DomainEventSubscriber> subscriberClass : subscribers) {
            ParameterizedType paramType = (ParameterizedType) subscriberClass.getGenericInterfaces()[0];
            Class<? extends DomainEvent> domainEventClass = (Class<? extends DomainEvent>) paramType.getActualTypeArguments()[0];
            DomainEventSubscriberInformation subscriberInformation = new DomainEventSubscriberInformation(subscriberClass, Collections.singletonList(domainEventClass));

            subscribersInformation.put(subscriberInformation.formatRabbitMqQueueName(), subscriberInformation);
        }

        return subscribersInformation;
    }

    public Collection<DomainEventSubscriberInformation> all() {
        return information.values();
    }

    public DomainEventSubscriberInformation searchRabbitMqQueue(String queue) throws Exception {
        DomainEventSubscriberInformation subscriberInformation = information.get(queue);

        if (subscriberInformation == null) {
            throw new Exception(String.format("There are not registered subscribers for <%s> queue", queue));
        }

        return subscriberInformation;
    }

    public String[] rabbitMqFormattedNames() {
        return information.values()
            .stream()
            .map(DomainEventSubscriberInformation::formatRabbitMqQueueName)
            .distinct()
            .toArray(String[]::new);
    }
}
