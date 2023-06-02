package com.shiproutes.ports.port.application.find;

import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.shared.domain.bus.query.Response;

public final class PortResponse implements Response {

    private final String id;
    private final String name;
    private final String locode;
    private final Double longitude;
    private final Double latitude;

    public PortResponse(String id, String name, String locode, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.locode = locode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static PortResponse from(Port port) {
        return new PortResponse(port.id().value(), port.name().value(), port.locode().value(),
            port.coordinates().longitude().value(), port.coordinates().latitude().value());
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
}
