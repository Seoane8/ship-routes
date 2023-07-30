package com.shiproutes.shared.domain.bus.event;

public interface DomainEventSubscriber<T extends DomainEvent> {

    void on(T event);

}
