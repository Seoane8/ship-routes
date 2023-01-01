package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class ShipRecorded extends DomainEvent {

    private final String name;
    private final Integer teus;

    public ShipRecorded() {
        super(null);
        this.name = null;
        this.teus = null;
    }

    public ShipRecorded(String aggregateId, String name, Integer teus) {
        super(aggregateId);
        this.name = name;
        this.teus = teus;
    }

    public ShipRecorded(String aggregateId, String eventId, String occurredOn, String name, Integer teus) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
        this.teus = teus;
    }

    @Override
    public String eventName() {
        return "ship.recorded";
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
        return new ShipRecorded(
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
        if (!(o instanceof ShipRecorded)) return false;
        ShipRecorded that = (ShipRecorded) o;
        return Objects.equals(name, that.name) && Objects.equals(teus, that.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teus);
    }
}
