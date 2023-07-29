package com.shiproutes.ingest.port_event.application.ingest;

import com.shiproutes.shared.domain.bus.command.Command;

import java.time.Instant;
import java.util.Objects;

public class IngestPortEventCommand implements Command {

    private final String locode;
    private final String portName;
    private final Double longitude;
    private final Double latitude;
    private final String imo;
    private final String shipName;
    private final Integer teus;
    private final Instant timestamp;
    private final String eventType;

    public IngestPortEventCommand(String locode, String portName, Double longitude, Double latitude,
                                  String imo, String shipName, Integer teus, Instant timestamp, String eventType) {
        this.locode = locode;
        this.portName = portName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imo = imo;
        this.shipName = shipName;
        this.teus = teus;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }

    public String locode() {
        return locode;
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

    public String imo() {
        return imo;
    }

    public String shipName() {
        return shipName;
    }

    public Integer teus() {
        return teus;
    }

    public Instant timestamp() {
        return timestamp;
    }

    public String eventType() {
        return eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngestPortEventCommand)) return false;
        IngestPortEventCommand that = (IngestPortEventCommand) o;
        return Objects.equals(locode, that.locode) && Objects.equals(portName, that.portName)
            && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude)
            && Objects.equals(imo, that.imo) && Objects.equals(shipName, that.shipName)
            && Objects.equals(teus, that.teus) && Objects.equals(timestamp, that.timestamp)
            && Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locode, portName, longitude, latitude, imo, shipName, teus, timestamp, eventType);
    }
}
