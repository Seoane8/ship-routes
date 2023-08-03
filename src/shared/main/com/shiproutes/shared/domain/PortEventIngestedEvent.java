package com.shiproutes.shared.domain;

import com.shiproutes.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

public class PortEventIngestedEvent extends DomainEvent {

    private final String locode;
    private final String imo;
    private final Instant date;
    private final String type;

    public PortEventIngestedEvent() {
        super(null);
        this.locode = null;
        this.imo = null;
        this.date = null;
        this.type = null;
    }

    public PortEventIngestedEvent(String aggregateId, String locode, String imo, Instant date, String type) {
        super(aggregateId);
        this.locode = locode;
        this.imo = imo;
        this.date = date;
        this.type = type;
    }

    public PortEventIngestedEvent(String aggregateId, String eventId, String occurredOn,
                                  String locode, String imo, Instant date, String type) {
        super(aggregateId, eventId, occurredOn);
        this.locode = locode;
        this.imo = imo;
        this.date = date;
        this.type = type;
    }

    @Override
    public String eventName() {
        return "portEvent.received";
    }

    public String locode() {
        return locode;
    }

    public String imo() {
        return imo;
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
            put("locode", locode);
            put("imo", imo);
            put("date", date.toString());
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
            (String) body.get("locode"),
            (String) body.get("imo"),
            Instant.parse(body.get("date").toString()),
            (String) body.get("type")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortEventIngestedEvent)) return false;
        PortEventIngestedEvent that = (PortEventIngestedEvent) o;
        return Objects.equals(locode, that.locode) && Objects.equals(imo, that.imo)
            && Objects.equals(date, that.date) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locode, imo, date, type);
    }
}
