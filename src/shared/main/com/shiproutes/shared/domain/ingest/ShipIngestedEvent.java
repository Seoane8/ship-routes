package com.shiproutes.shared.domain.ingest;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class ShipIngestedEvent extends DomainEvent {

    private final String name;
    private final Integer teus;

    public ShipIngestedEvent() {
        super(null);
        this.name = null;
        this.teus = null;
    }

    public ShipIngestedEvent(String aggregateId, String name, Integer teus) {
        super(aggregateId);
        this.name = name;
        this.teus = teus;
    }

    public ShipIngestedEvent(String aggregateId, String eventId, String occurredOn, String name, Integer teus) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
        this.teus = teus;
    }

    @Override
    public String eventName() {
        return "ship.received";
    }

    public String name() {
        return name;
    }

    public Integer teus() {
        return teus;
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
        return new ShipIngestedEvent(
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
        if (!(o instanceof ShipIngestedEvent)) return false;
        ShipIngestedEvent that = (ShipIngestedEvent) o;
        return Objects.equals(name, that.name) && Objects.equals(teus, that.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teus);
    }
}
