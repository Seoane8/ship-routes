package com.shiproutes.ports.port_event.domain;

import com.shiproutes.ports.shared.domain.Coordinates;
import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Teus;

import java.util.Objects;

public final class PortEvent extends AggregateRoot {

    private final PortEventId id;
    private final PortEventType type;
    private final PortId portId;
    private final Coordinates coordinates;
    private final IMO shipId;
    private final Teus teus;
    private final PortEventDate date;

    public PortEvent(PortEventId id, PortEventType type, PortId portId, Coordinates coordinates, IMO shipId, Teus teus,
                     PortEventDate date) {
        this.id = id;
        this.type = type;
        this.portId = portId;
        this.coordinates = coordinates;
        this.shipId = shipId;
        this.teus = teus;
        this.date = date;
    }

    public static PortEvent create(PortEventId id, PortEventType type, PortId portId, Coordinates coordinates,
                                   IMO shipId, Teus teus, PortEventDate date) {
        PortEvent portEvent = new PortEvent(id, type, portId, coordinates, shipId, teus, date);

        portEvent.record(new PortEventCreated(
            id.value(),
            portId.value(),
            type.value(),
            shipId.value(),
            date.value()
        ));

        return portEvent;
    }

    public PortEventId id() {
        return id;
    }

    public PortEventType type() {
        return type;
    }

    public PortId portId() {
        return portId;
    }

    public IMO shipId() {
        return shipId;
    }

    public Coordinates coordinates() {
        return coordinates;
    }

    public Teus teus() {
        return teus;
    }

    public PortEventDate date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof PortEvent)) return false;
        PortEvent portEvent = (PortEvent) o;
        return Objects.equals(id, portEvent.id) && type == portEvent.type && Objects.equals(portId, portEvent.portId)
            && Objects.equals(coordinates, portEvent.coordinates) && Objects.equals(shipId, portEvent.shipId)
            && Objects.equals(teus, portEvent.teus) && Objects.equals(date, portEvent.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, portId, coordinates, shipId, teus, date);
    }
}
