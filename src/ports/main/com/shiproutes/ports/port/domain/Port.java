package com.shiproutes.ports.port.domain;

import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.coordinates.Coordinates;

import java.util.Objects;

public final class Port extends AggregateRoot {
    private final PortId id;
    private final PortName name;
    private final Locode locode;
    private final Coordinates coordinates;
    private TotalDepartures totalDepartures;
    private TotalArrivals totalArrivals;

    public Port(PortId id, PortName name, Locode locode, Coordinates coordinates,
                TotalDepartures totalDepartures, TotalArrivals totalArrivals) {
        this.id = id;
        this.name = name;
        this.locode = locode;
        this.coordinates = coordinates;
        this.totalDepartures = totalDepartures;
        this.totalArrivals = totalArrivals;
    }

    public static Port create(PortId id, PortName name, Locode locode, Coordinates coordinates) {
        Port port = new Port(id, name, locode, coordinates, TotalDepartures.initialize(), TotalArrivals.initialize());

        port.record(new PortCreatedEvent(
            id.value(),
            name.value(),
            locode.value(),
            coordinates.latitude().value(),
            coordinates.longitude().value()
        ));

        return port;
    }

    public PortId id() {
        return id;
    }

    public PortName name() {
        return name;
    }

    public Locode locode() {
        return locode;
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public TotalDepartures totalDepartures() {
        return totalDepartures;
    }

    public void incrementDepartures() {
        totalDepartures = totalDepartures.increment();
    }

    public TotalArrivals totalArrivals() {
        return totalArrivals;
    }

    public void incrementArrivals() {
        totalArrivals = totalArrivals.increment();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Port)) return false;
        Port port = (Port) o;
        return Objects.equals(id, port.id) && Objects.equals(name, port.name) && Objects.equals(locode, port.locode)
            && Objects.equals(coordinates, port.coordinates) && Objects.equals(totalDepartures, port.totalDepartures)
            && Objects.equals(totalArrivals, port.totalArrivals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, locode, coordinates, totalDepartures, totalArrivals);
    }
}
