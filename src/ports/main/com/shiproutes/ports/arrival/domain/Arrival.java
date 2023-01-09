package com.shiproutes.ports.arrival.domain;

import com.shiproutes.ports.shared.domain.PortId;
import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.IMO;

import java.util.Objects;

public final class Arrival extends AggregateRoot {

    private final ArrivalId id;
    private final PortId portId;
    private final IMO shipId;
    private final ArrivalDate date;

    public Arrival(ArrivalId id, PortId portId, IMO shipId, ArrivalDate date) {
        this.id = id;
        this.portId = portId;
        this.shipId = shipId;
        this.date = date;
    }

    public static Arrival create(ArrivalId id, PortId portId, IMO shipId, ArrivalDate date) {
        Arrival arrival = new Arrival(id, portId, shipId, date);

        arrival.record(new ArrivalCreated(
            id.value(),
            portId.value(),
            shipId.value(),
            date.value()
        ));

        return arrival;
    }

    public ArrivalId id() {
        return id;
    }

    public PortId portId() {
        return portId;
    }

    public IMO shipId() {
        return shipId;
    }

    public ArrivalDate date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arrival)) return false;
        Arrival arrival = (Arrival) o;
        return Objects.equals(id, arrival.id) && Objects.equals(portId, arrival.portId) && Objects.equals(shipId,
            arrival.shipId) && Objects.equals(date, arrival.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portId, shipId, date);
    }
}
