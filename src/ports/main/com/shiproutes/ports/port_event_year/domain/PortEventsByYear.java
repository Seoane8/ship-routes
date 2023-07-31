package com.shiproutes.ports.port_event_year.domain;

import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.ports.shared.domain.TeusCounter;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Objects;

public final class PortEventsByYear {

    private final PortEventsByYearId id;
    private final PortId portId;
    private final PortName portName;
    private final Coordinates coordinates;
    private final Year year;
    private TotalDepartures departures;
    private TotalArrivals arrivals;
    private TeusCounter teusCounter;

    public PortEventsByYear(PortEventsByYearId id, PortId portId, PortName portName, Coordinates coordinates, Year year,
                            TotalDepartures departures, TotalArrivals arrivals, TeusCounter teusCounter) {
        this.id = id;
        this.portId = portId;
        this.portName = portName;
        this.coordinates = coordinates;
        this.year = year;
        this.departures = departures;
        this.arrivals = arrivals;
        this.teusCounter = teusCounter;
    }

    public static PortEventsByYear create(PortEventsByYearId id, PortId portId, PortName portName,
                                          Coordinates coordinates, Year year) {
        return new PortEventsByYear(id, portId, portName, coordinates, year,
            TotalDepartures.initialize(), TotalArrivals.initialize(), TeusCounter.initialize());
    }

    public PortEventsByYearId id() {
        return id;
    }

    public PortId portId() {
        return portId;
    }

    public PortName portName() {
        return portName;
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

    public void incrementDepartures() {
        departures = departures.increment();
    }

    public TotalArrivals arrivals() {
        return arrivals;
    }

    public void incrementArrivals() {
        arrivals = arrivals.increment();
    }

    public TeusCounter teusCounter() {
        return teusCounter;
    }

    public void incrementTeus(Teus teus) {
        teusCounter = teusCounter.increment(teus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortEventsByYear)) return false;
        PortEventsByYear that = (PortEventsByYear) o;
        return Objects.equals(id, that.id) && Objects.equals(portId, that.portId)
            && Objects.equals(portName, that.portName) && Objects.equals(coordinates, that.coordinates)
            && Objects.equals(year, that.year) && Objects.equals(departures, that.departures)
            && Objects.equals(arrivals, that.arrivals) && Objects.equals(teusCounter, that.teusCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portId, portName, coordinates, year, departures, arrivals, teusCounter);
    }
}
