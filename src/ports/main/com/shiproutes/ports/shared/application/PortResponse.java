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
    private final Long totalArrivals;
    private final Integer teus;

    public PortResponse(String id, String name, String locode, Double longitude, Double latitude,
                        Long totalDepartures, Long totalArrivals, Integer teus) {
        this.id = id;
        this.name = name;
        this.locode = locode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.totalDepartures = totalDepartures;
        this.totalArrivals = totalArrivals;
        this.teus = teus;
    }

    public static PortResponse from(Port port) {
        return new PortResponse(port.id().value(), port.name().value(), port.locode().value(),
            port.coordinates().longitude().value(), port.coordinates().latitude().value(),
            port.totalDepartures().value(), port.totalArrivals().value(), port.teus().value());
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

    public Long totalArrivals() {
        return totalArrivals;
    }

    public Integer teus() {
        return teus;
    }
}
