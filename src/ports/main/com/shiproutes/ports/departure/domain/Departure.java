package com.shiproutes.ports.departure.domain;

import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortId;

import java.util.Objects;

public final class Departure extends AggregateRoot {

    private final DepartureId id;
    private final PortId portId;
    private final IMO shipId;
    private final DepartureDate date;

    public Departure(DepartureId id, PortId portId, IMO shipId, DepartureDate date) {
        this.id = id;
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
    }

    public static Departure create(DepartureId id, PortId portId, IMO shipId, DepartureDate date) {
        Departure departure = new Departure(id, portId, shipId, date);

        departure.record(new DepartureCreated(
            id.value(),
            portId.value(),
            shipId.value(),
            date.value()
        ));

        return departure;
    }

    public DepartureId id() {
        return id;
    }

    public PortId portId() {
        return portId;
    }

    public IMO shipId() {
        return shipId;
    }

    public DepartureDate date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departure)) return false;
        Departure departure = (Departure) o;
        return Objects.equals(id, departure.id) && Objects.equals(portId, departure.portId) && Objects.equals(shipId,
            departure.shipId) && Objects.equals(date, departure.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portId, shipId, date);
    }
}
