package com.shiproutes.ports.departure.domain;

import com.shiproutes.ports.shared.domain.Coordinates;
import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Teus;

import java.util.Objects;

public final class Departure extends AggregateRoot {

    private final DepartureId id;
    private final PortId portId;
    private final Coordinates coordinates;
    private final IMO shipId;
    private final Teus teus;
    private final DepartureDate date;

    public Departure(DepartureId id, PortId portId, Coordinates coordinates, IMO shipId, Teus teus, DepartureDate date) {
        this.id = id;
        this.portId = portId;
        this.coordinates = coordinates;
        this.shipId = shipId;
        this.teus = teus;
        this.date = date;
    }

    public static Departure create(DepartureId id, PortId portId, Coordinates coordinates, IMO shipId, Teus teus, DepartureDate date) {
        Departure departure = new Departure(id, portId, coordinates, shipId, teus, date);

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

    public Coordinates coordinates() {
        return coordinates;
    }

    public IMO shipId() {
        return shipId;
    }

    public Teus teus() {
        return teus;
    }

    public DepartureDate date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departure)) return false;
        Departure departure = (Departure) o;
        return Objects.equals(id, departure.id) && Objects.equals(portId, departure.portId)
            && Objects.equals(coordinates, departure.coordinates) && Objects.equals(shipId, departure.shipId)
            && Objects.equals(teus, departure.teus) && Objects.equals(date, departure.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portId, coordinates, shipId, teus, date);
    }
}
