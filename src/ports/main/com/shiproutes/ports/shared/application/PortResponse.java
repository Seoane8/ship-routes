package com.shiproutes.ports.shared.application;

import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.shared.domain.bus.query.Response;

public final class PortResponse implements Response {

    private final String id;
    private final String name;
    private final String locode;
    private final Double longitude;
    private final Double latitude;
    private final Long totalDepartures;

    public PortResponse(String id, String name, String locode, Double longitude, Double latitude,
                        Long totalDepartures) {
        this.id = id;
        this.name = name;
        this.locode = locode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.totalDepartures = totalDepartures;
    }

    public static PortResponse from(Port port) {
        return new PortResponse(port.id().value(), port.name().value(), port.locode().value(),
            port.coordinates().longitude().value(), port.coordinates().latitude().value(),
            port.totalDepartures().value());
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String locode() {
        return locode;
    }

    public Double longitude() {
        return longitude;
    }

    public Double latitude() {
        return latitude;
    }

    public Long totalDepartures() {
        return totalDepartures;
    }
}
