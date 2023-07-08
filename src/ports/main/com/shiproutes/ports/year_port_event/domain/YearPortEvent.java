package com.shiproutes.ports.year_port_event.domain;

import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.Coordinates;
import com.shiproutes.shared.domain.PortId;

import java.util.Objects;

public final class YearPortEvent {

    private final YearPortEventId id;
    private final PortEventType type;
    private final PortId portId;
    private final Coordinates coordinates;
    private final Year year;
    private final TotalDepartures departures;
    private final TotalArrivals arrivals;

    public YearPortEvent(YearPortEventId id, PortEventType type, PortId portId, Coordinates coordinates, Year year,
                         TotalDepartures departures, TotalArrivals arrivals) {
        this.id = id;
        this.type = type;
        this.portId = portId;
        this.coordinates = coordinates;
        this.year = year;
        this.departures = departures;
        this.arrivals = arrivals;
    }

    public YearPortEventId id() {
        return id;
    }

    public PortEventType type() {
        return type;
    }

    public PortId portId() {
        return portId;
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public Year year() {
        return year;
    }

    public TotalDepartures departures() {
        return departures;
    }

    public TotalArrivals arrivals() {
        return arrivals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YearPortEvent)) return false;
        YearPortEvent that = (YearPortEvent) o;
        return Objects.equals(id, that.id) && type == that.type && Objects.equals(portId, that.portId)
            && Objects.equals(coordinates, that.coordinates) && Objects.equals(year, that.year)
            && Objects.equals(departures, that.departures) && Objects.equals(arrivals, that.arrivals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, portId, coordinates, year, departures, arrivals);
    }
}
