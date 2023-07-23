package com.shiproutes.routes.shared.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public class JourneyRemovedEvent extends DomainEvent {

    private final String shipId;
    private final String originPort;
    private final String destinationPort;
    private final Instant departureDate;
    private final Instant arrivalDate;

    public JourneyRemovedEvent() {
        super(null);
        this.shipId = null;
        this.originPort = null;
        this.destinationPort = null;
        this.departureDate = null;
        this.arrivalDate = null;
    }

    public JourneyRemovedEvent(String aggregateId, String shipId, String originPort, String destinationPort,
                               Instant departureDate, Instant arrivalDate) {
        super(aggregateId);
        this.shipId = shipId;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public JourneyRemovedEvent(String aggregateId, String eventId, String occurredOn, String shipId,
                               String originPort, String destinationPort, Instant departureDate, Instant arrivalDate) {
        super(aggregateId, eventId, occurredOn);
        this.shipId = shipId;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String eventName() {
        return "journey.removed";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("shipId", shipId);
            put("originPort", originPort);
            put("destinationPort", destinationPort);
            put("departureDate", departureDate);
            put("arrivalDate", arrivalDate);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new JourneyRemovedEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("shipId"),
            (String) body.get("originPort"),
            (String) body.get("destinationPOrt"),
            (Instant) body.get("departureDate"),
            (Instant) body.get("arrivalDate")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyRemovedEvent)) return false;
        JourneyRemovedEvent that = (JourneyRemovedEvent) o;
        return Objects.equals(shipId, that.shipId)
            && Objects.equals(originPort, that.originPort) && Objects.equals(destinationPort, that.destinationPort)
            && Objects.equals(departureDate, that.departureDate) && Objects.equals(arrivalDate, that.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipId, originPort, destinationPort, departureDate, arrivalDate);
    }
}