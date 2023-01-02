package com.shiproutes.ports.port.application.create;

import com.shiproutes.shared.domain.bus.command.Command;

public final class CreatePortCommand implements Command {

    private final String id;
    private final String name;
    private final String locode;
    private final Double latitude;
    private final Double longitude;

    public CreatePortCommand(String id, String name, String locode, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.locode = locode;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }
}
