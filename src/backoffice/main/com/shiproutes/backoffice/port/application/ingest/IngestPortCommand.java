package com.shiproutes.backoffice.port.application.ingest;

import com.shiproutes.shared.domain.bus.command.Command;

import java.util.Objects;

public class IngestPortCommand implements Command {

    private final String locode;
    private final String name;
    private final Double latitude;
    private final Double longitude;

    public IngestPortCommand(String locode, String name, Double latitude, Double longitude) {
        this.locode = locode;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String locode() {
        return locode;
    }

    public String name() {
        return name;
    }

    public Double latitude() {
        return latitude;
    }

    public Double longitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngestPortCommand)) return false;
        IngestPortCommand that = (IngestPortCommand) o;
        return Objects.equals(locode, that.locode) && Objects.equals(name, that.name)
            && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locode, name, latitude, longitude);
    }
}
