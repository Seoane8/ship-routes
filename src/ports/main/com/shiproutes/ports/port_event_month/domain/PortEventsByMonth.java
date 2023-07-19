package com.shiproutes.ports.port_event_month.domain;

import com.shiproutes.ports.port_event_year.domain.Year;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.coordinates.Coordinates;

import java.util.Objects;

public class PortEventsByMonth {

    private final PortEventsByMonthId id;
    private final PortId portId;
    private final Coordinates coordinates;
    private final Year year;
    private final Month month;
    private TotalDepartures departures;
    private TotalArrivals arrivals;

    public PortEventsByMonth(PortEventsByMonthId id, PortId portId, Coordinates coordinates, Year year, Month month,
                             TotalDepartures departures, TotalArrivals arrivals) {
        this.id = id;
        this.portId = portId;
        this.coordinates = coordinates;
        this.year = year;
        this.month = month;
        this.departures = departures;
        this.arrivals = arrivals;
    }

    public static PortEventsByMonth create(PortEventsByMonthId id, PortId portId, Coordinates coordinates,
                                           Year year, Month month) {
        return new PortEventsByMonth(id, portId, coordinates, year, month,
            TotalDepartures.initialize(), TotalArrivals.initialize());
    }

    public PortEventsByMonthId id() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortEventsByMonth)) return false;
        PortEventsByMonth that = (PortEventsByMonth) o;
        return Objects.equals(id, that.id) && Objects.equals(portId, that.portId)
            && Objects.equals(coordinates, that.coordinates) && Objects.equals(year, that.year)
            && Objects.equals(month, that.month) && Objects.equals(departures, that.departures)
            && Objects.equals(arrivals, that.arrivals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portId, coordinates, year, month, departures, arrivals);
    }
}
