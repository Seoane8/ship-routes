package com.shiproutes.routes.shared.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public class JourneyRemovedEvent extends DomainEvent {

    private final String shipId;
    private final Integer teus;
    private final String originPort;
    private final String destinationPort;
    private final Instant departureDate;
    private final Instant arrivalDate;

    public JourneyRemovedEvent() {
        super(null);
        this.shipId = null;
        this.teus = null;
        this.originPort = null;
        this.destinationPort = null;
        this.departureDate = null;
        this.arrivalDate = null;
    }

    public JourneyRemovedEvent(String aggregateId, String shipId, Integer teus, String originPort,
                               String destinationPort, Instant departureDate, Instant arrivalDate) {
        super(aggregateId);
        this.shipId = shipId;
        this.teus = teus;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public JourneyRemovedEvent(String aggregateId, String eventId, String occurredOn, String shipId, Integer teus,
                               String originPort, String destinationPort, Instant departureDate, Instant arrivalDate) {
        super(aggregateId, eventId, occurredOn);
        this.shipId = shipId;
        this.teus = teus;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String eventName() {
        return "journey.removed";
    }

    public String originPort() {
        return originPort;
    }

    public String destinationPort() {
        return destinationPort;
    }

    public Instant departureDate() {
        return departureDate;
    }

    public Integer teus() {
        return teus;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("shipId", shipId);
            put("teus", teus);
            put("originPort", originPort);
            put("destinationPort", destinationPort);
            put("departureDate", departureDate.toString());
            put("arrivalDate", arrivalDate.toString());
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new JourneyRemovedEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("shipId"),
            (Integer) body.get("teus"),
            (String) body.get("originPort"),
            (String) body.get("destinationPOrt"),
            Instant.parse(body.get("departureDate").toString()),
            Instant.parse(body.get("arrivalDate").toString())
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyRemovedEvent)) return false;
        JourneyRemovedEvent that = (JourneyRemovedEvent) o;
        return Objects.equals(shipId, that.shipId) && Objects.equals(teus, that.teus)
            && Objects.equals(originPort, that.originPort) && Objects.equals(destinationPort, that.destinationPort)
            && Objects.equals(departureDate, that.departureDate) && Objects.equals(arrivalDate, that.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipId, teus, originPort, destinationPort, departureDate, arrivalDate);
    }
}
