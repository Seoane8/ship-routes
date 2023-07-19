package com.shiproutes.shared.domain.ports;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class PortCreatedEvent extends DomainEvent {

    private final String name;
    private final String locode;
    private final Double latitude;
    private final Double longitude;

    public PortCreatedEvent() {
        super(null);
        this.name = null;
        this.locode = null;
        this.latitude = null;
        this.longitude = null;

    }

    public PortCreatedEvent(String aggregateId, String name, String locode, Double latitude, Double longitude) {
        super(aggregateId);
        this.name = name;
        this.locode = locode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PortCreatedEvent(String aggregateId, String eventId, String occurredOn, String name, String locode,
                            Double latitude, Double longitude) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
        this.locode = locode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String eventName() {
        return "port.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("name", name);
            put("locode", locode);
            put("latitude", latitude);
            put("longitude", longitude);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId,
                                      String occurredOn) {
        return new PortCreatedEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("name"),
            (String) body.get("locode"),
            (Double) body.get("latitude"),
            (Double) body.get("longitude")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortCreatedEvent)) return false;
        PortCreatedEvent that = (PortCreatedEvent) o;
        return Objects.equals(name, that.name) && Objects.equals(locode, that.locode) && Objects.equals(latitude,
            that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, locode, latitude, longitude);
    }
}
