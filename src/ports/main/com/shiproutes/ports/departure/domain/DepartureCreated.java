package com.shiproutes.ports.departure.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public final class DepartureCreated extends DomainEvent {

    private final String portId;
    private final String shipId;
    private final Instant date;

    public DepartureCreated() {
        super(null);
        this.portId = null;
        this.shipId = null;
        this.date = null;
    }

    public DepartureCreated(String aggregateId, String portId, String shipId, Instant date) {
        super(aggregateId);
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
    }

    public DepartureCreated(String aggregateId, String eventId, String occurredOn, String portId, String shipId,
                            Instant date) {
        super(aggregateId, eventId, occurredOn);
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
    }

    @Override
    public String eventName() {
        return "arrival.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("portId", portId);
            put("shipId", shipId);
            put("date", date);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId,
                                      String occurredOn) {
        return new DepartureCreated(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("portId"),
            (String) body.get("shipId"),
            (Instant) body.get("date")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DepartureCreated)) return false;
        DepartureCreated that = (DepartureCreated) o;
        return Objects.equals(portId, that.portId) && Objects.equals(shipId, that.shipId) && Objects.equals(date,
            that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portId, shipId, date);
    }
}
