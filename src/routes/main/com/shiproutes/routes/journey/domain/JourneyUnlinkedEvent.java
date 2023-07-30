package com.shiproutes.routes.journey.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public class JourneyUnlinkedEvent extends DomainEvent {

    private final String shipId;
    private final String portId;
    private final Instant date;
    private final String type;

    public JourneyUnlinkedEvent() {
        super(null);
        this.shipId = null;
        this.portId = null;
        this.date = null;
        this.type = null;
    }

    public JourneyUnlinkedEvent(String aggregateId, String shipId, String portId, Instant date, String type) {
        super(aggregateId);
        this.shipId = shipId;
        this.portId = portId;
        this.date = date;
        this.type = type;
    }

    public JourneyUnlinkedEvent(String aggregateId, String eventId, String occurredOn,
                                String shipId, String portId, Instant date, String type) {
        super(aggregateId, eventId, occurredOn);
        this.shipId = shipId;
        this.portId = portId;
        this.date = date;
        this.type = type;
    }

    @Override
    public String eventName() {
        return "journey.unlinked";
    }

    public String shipId() {
        return shipId;
    }

    public String portId() {
        return portId;
    }

    public Instant date() {
        return date;
    }

    public String type() {
        return type;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("shipId", shipId);
            put("portId", portId);
            put("date", date.toString());
            put("type", type);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new JourneyUnlinkedEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("shipId"),
            (String) body.get("portId"),
            Instant.parse(body.get("date").toString()),
            (String) body.get("type"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyUnlinkedEvent)) return false;
        JourneyUnlinkedEvent that = (JourneyUnlinkedEvent) o;
        return Objects.equals(shipId, that.shipId) && Objects.equals(portId, that.portId)
            && Objects.equals(date, that.date) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipId, portId, date, type);
    }
}
