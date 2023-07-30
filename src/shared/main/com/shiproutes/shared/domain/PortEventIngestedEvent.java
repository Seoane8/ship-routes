package com.shiproutes.shared.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public class PortEventIngestedEvent extends DomainEvent {

    private final String portId;
    private final String shipId;
    private final Instant date;
    private final String type;

    public PortEventIngestedEvent() {
        super(null);
        this.portId = null;
        this.shipId = null;
        this.date = null;
        this.type = null;
    }

    public PortEventIngestedEvent(String aggregateId, String portId, String shipId, Instant date, String type) {
        super(aggregateId);
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
        this.type = type;
    }

    public PortEventIngestedEvent(String aggregateId, String eventId, String occurredOn,
                                  String portId, String shipId, Instant date, String type) {
        super(aggregateId, eventId, occurredOn);
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
        this.type = type;
    }

    @Override
    public String eventName() {
        return "portEvent.received";
    }

    public String portId() {
        return portId;
    }

    public String shipId() {
        return shipId;
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
            put("portId", portId);
            put("shipId", shipId);
            put("date", date);
            put("type", type);

        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body,
                                      String eventId, String occurredOn) {
        return new PortEventIngestedEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("portId"),
            (String) body.get("shipId"),
            (Instant) body.get("date"),
            (String) body.get("type")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortEventIngestedEvent)) return false;
        PortEventIngestedEvent that = (PortEventIngestedEvent) o;
        return Objects.equals(portId, that.portId) && Objects.equals(shipId, that.shipId)
            && Objects.equals(date, that.date) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portId, shipId, date, type);
    }
}
