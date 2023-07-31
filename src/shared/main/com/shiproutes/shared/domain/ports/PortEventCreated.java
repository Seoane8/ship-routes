package com.shiproutes.shared.domain.ports;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public final class PortEventCreated extends DomainEvent {

    private final String portId;
    private final String portName;
    private final String type;
    private final String shipId;
    private final Instant date;
    private final Integer teus;

    public PortEventCreated() {
        super(null);
        this.portId = null;
        this.portName = null;
        this.type = null;
        this.shipId = null;
        this.date = null;
        this.teus = null;
    }

    public PortEventCreated(String aggregateId, String portId, String portName, String type, String shipId, Instant date, Integer teus) {
        super(aggregateId);
        this.portName = portName;
        this.type = type;
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
        this.teus = teus;
    }

    public PortEventCreated(String aggregateId, String eventId, String occurredOn, String portId, String portName, String type,
                            String shipId, Instant date, Integer teus) {
        super(aggregateId, eventId, occurredOn);
        this.portName = portName;
        this.type = type;
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
        this.teus = teus;
    }

    @Override
    public String eventName() {
        return "portEvent.created";
    }

    public String portId() {
        return portId;
    }

    public String type() {
        return type;
    }

    public String shipId() {
        return shipId;
    }

    public Instant date() {
        return date;
    }

    public Integer teus() {
        return teus;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("portId", portId);
            put("portName", portName);
            put("type", type);
            put("shipId", shipId);
            put("date", date.toString());
            put("teus", teus);
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
            (String) body.get("portName"),
            (String) body.get("type"),
            (String) body.get("shipId"),
            Instant.parse(body.get("date").toString()),
            (Integer) body.get("teus")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortEventCreated)) return false;
        PortEventCreated that = (PortEventCreated) o;
        return Objects.equals(portId, that.portId) && Objects.equals(portName, that.portName)
            && Objects.equals(type, that.type) && Objects.equals(shipId, that.shipId)
            && Objects.equals(date, that.date) && Objects.equals(teus, that.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portId, portName, type, shipId, date, teus);
    }
}
