package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public final class PortEventCreated extends DomainEvent {

    private final String portId;
    private final String shipId;
    private final Instant date;

    public PortEventCreated() {
        super(null);
        this.portId = null;
        this.shipId = null;
        this.date = null;
    }

    public PortEventCreated(String aggregateId, String portId, String shipId, Instant date) {
        super(aggregateId);
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
    }

    public PortEventCreated(String aggregateId, String eventId, String occurredOn, String portId, String shipId,
                          Instant date) {
        super(aggregateId, eventId, occurredOn);
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
    }

    @Override
    public String eventName() {
        return "portEvent.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("portId", portId);
            put("shipId", shipId);
            put("date", date);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId,
                                      String occurredOn) {
        return new PortEventCreated(
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
        if (!(o instanceof PortEventCreated)) return false;
        PortEventCreated that = (PortEventCreated) o;
        return Objects.equals(portId, that.portId) && Objects.equals(shipId, that.shipId) && Objects.equals(date,
            that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portId, shipId, date);
    }
}
