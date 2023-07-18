package com.shiproutes.ports.port_event.application;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.shared.domain.bus.query.Response;

public class PortEventsBetweenDatesResponse implements Response {

    private final String portId;
    private final Double longitude;
    private final Double latitude;
    private Long departures;
    private Long arrivals;

    public PortEventsBetweenDatesResponse(String portId, Double longitude, Double latitude,
                                          Long departures, Long arrivals) {
        this.portId = portId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.departures = departures;
        this.arrivals = arrivals;
    }

    public static PortEventsBetweenDatesResponse from(PortEvent entity) {
        return new PortEventsBetweenDatesResponse(entity.portId().value(),
            entity.coordinates().longitude().value(), entity.coordinates().latitude().value(),
            0L, 0L);
    }

    public String portId() {
        return portId;
    }

    public Double longitude() {
        return longitude;
    }

    public Double latitude() {
        return latitude;
    }

    public Long departures() {
        return departures;
    }

    public Long arrivals() {
        return arrivals;
    }

    public void addEvents(PortEvent event) {
        if (event.type() == PortEventType.ARRIVAL) {
            this.arrivals++;
        } else {
            this.departures++;
        }
    }

}