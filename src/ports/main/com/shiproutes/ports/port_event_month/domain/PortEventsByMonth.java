package com.shiproutes.ports.port_event_month.domain;

import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.ports.TeusCounter;

import java.util.Objects;

public class PortEventsByMonth {

    private final PortEventsByMonthId id;
    private final PortId portId;
    private final PortName portName;
    private final Coordinates coordinates;
    private final Year year;
    private final Month month;
    private TotalDepartures departures;
    private TotalArrivals arrivals;
    private TeusCounter teusCounter;

    public PortEventsByMonth(PortEventsByMonthId id, PortId portId, PortName portName, Coordinates coordinates,
                             Year year, Month month, TotalDepartures departures, TotalArrivals arrivals,
                             TeusCounter teusCounter) {
        this.id = id;
        this.portId = portId;
        this.portName = portName;
        this.coordinates = coordinates;
        this.year = year;
        this.month = month;
        this.departures = departures;
        this.arrivals = arrivals;
        this.teusCounter = teusCounter;
    }

    public static PortEventsByMonth create(PortEventsByMonthId id, PortId portId, PortName portName,
                                           Coordinates coordinates, Year year, Month month) {
        return new PortEventsByMonth(id, portId, portName, coordinates, year, month,
            TotalDepartures.initialize(), TotalArrivals.initialize(), TeusCounter.initialize());
    }

    public PortEventsByMonthId id() {
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

    public Month month() {
        return month;
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
        if (!(o instanceof PortEventsByMonth)) return false;
        PortEventsByMonth that = (PortEventsByMonth) o;
        return Objects.equals(id, that.id) && Objects.equals(portId, that.portId)
            && Objects.equals(portName, that.portName) && Objects.equals(coordinates, that.coordinates)
            && Objects.equals(year, that.year) && Objects.equals(month, that.month)
            && Objects.equals(departures, that.departures) && Objects.equals(arrivals, that.arrivals)
            && Objects.equals(teusCounter, that.teusCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portId, portName, coordinates, year, month, departures, arrivals, teusCounter);
    }
}
