package com.shiproutes.ports.port_event.application;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.shared.domain.bus.query.Response;

public class PortEventsBetweenDatesResponse implements Response {

    private final String portId;
    private final String portName;
    private final Double longitude;
    private final Double latitude;
    private Long departures;
    private Long arrivals;
    private Integer teus;

    public PortEventsBetweenDatesResponse(String portId, String portName, Double longitude, Double latitude,
                                          Long departures, Long arrivals, Integer teus) {
        this.portId = portId;
        this.portName = portName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.departures = departures;
        this.arrivals = arrivals;
        this.teus = teus;
    }

    public static PortEventsBetweenDatesResponse from(PortEvent entity) {
        return new PortEventsBetweenDatesResponse(entity.portId().value(), entity.portName().value(),
            entity.coordinates().longitude().value(), entity.coordinates().latitude().value(),
            0L, 0L, 0);
    }

    public String portId() {
        return portId;
    }

    public String portName() {
        return portName;
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

    public Integer teus() {
        return teus;
    }

    public void addEvents(PortEvent event) {
        if (event.type() == PortEventType.ARRIVAL) {
            this.arrivals++;
        } else {
            this.departures++;
        }
        this.teus += event.teus().value();
    }
}
