package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class ShipCreatedEvent extends DomainEvent {

    private final String name;
    private final Integer teus;

    public ShipCreatedEvent() {
        super(null);
        this.name = null;
        this.teus = null;
    }

    public ShipCreatedEvent(String aggregateId, String name, Integer teus) {
        super(aggregateId);
        this.name = name;
        this.teus = teus;
    }

    public ShipCreatedEvent(String aggregateId, String eventId, String occurredOn, String name, Integer teus) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
        this.teus = teus;
    }

    @Override
    public String eventName() {
        return "ship.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("name", name);
            put("teus", teus);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId,
                                      String occurredOn) {
        return new ShipCreatedEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("name"),
            (Integer) body.get("teus")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShipCreatedEvent)) return false;
        ShipCreatedEvent that = (ShipCreatedEvent) o;
        return Objects.equals(name, that.name) && Objects.equals(teus, that.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teus);
    }
}
