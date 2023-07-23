package com.shiproutes.routes.journey.domain;

import com.shiproutes.routes.shared.domain.JourneyCreatedEvent;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Objects;

public class Journey extends AggregateRoot {

    private final JourneyId id;
    private final IMO shipId;
    private final PortId originPort;
    private final PortId destinationPort;
    private final DepartureDate departureDate;
    private final ArrivalDate arrivalDate;
    private final RoutePath path;

    public Journey(JourneyId id, IMO shipId, PortId originPort, PortId destinationPort,
                   DepartureDate departureDate, ArrivalDate arrivalDate, RoutePath path) {
        this.id = id;
        this.shipId = shipId;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.path = path;
    }

    public static Journey create(JourneyId id, IMO shipId, PortId originPort, PortId destinationPort,
                                 DepartureDate departureDate, ArrivalDate arrivalDate, RoutePath path) {
        Journey journey = new Journey(id, shipId, originPort, destinationPort, departureDate, arrivalDate, path);

        journey.record(new JourneyCreatedEvent(
            id.value(),
            shipId.value(),
            originPort.value(),
            destinationPort.value(),
            departureDate.value(),
            arrivalDate.value())
        );

        return journey;
    }

    public static Journey departure(JourneyId journeyId, IMO shipId, PortId originPort, DepartureDate departureDate) {
        return new Journey(journeyId, shipId, originPort, PortId.empty(), departureDate, ArrivalDate.empty(),
            RoutePath.empty());
    }

    public static Journey arrival(JourneyId journeyId, IMO shipId, PortId destinationPort, ArrivalDate arrivalDate) {
        return new Journey(journeyId, shipId, PortId.empty(), destinationPort, DepartureDate.empty(), arrivalDate,
            RoutePath.empty());
    }

    public JourneyId id() {
        return id;
    }

    public IMO shipId() {
        return shipId;
    }

    public PortId originPort() {
        return originPort;
    }

    public PortId destinationPort() {
        return destinationPort;
    }

    public DepartureDate departureDate() {
        return departureDate;
    }

    public ArrivalDate arrivalDate() {
        return arrivalDate;
    }

    public RoutePath path() {
        return path;
    }

    public boolean isComplete() {
        return !arrivalDate.isEmpty() && !departureDate.isEmpty() && !path.isEmpty();
    }

    public void recordRemovedEvent() {
        this.record(new JourneyRemovedEvent(
            id.value(),
            shipId.value(),
            originPort.value(),
            destinationPort.value(),
            departureDate.value(),
            arrivalDate.value()
        ));
    }

    public void recordUnlinkedDepartureEvent() {
        this.record(new JourneyUnlinkedEvent(
            id.value(),
            shipId.value(),
            originPort.value(),
            departureDate.value(),
            "DEPARTURE"
        ));
    }

    public void recordUnlinkedArrivalEvent() {
        this.record(new JourneyUnlinkedEvent(
            id.value(),
            shipId.value(),
            destinationPort.value(),
            arrivalDate.value(),
            "ARRIVAL"
        ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journey)) return false;
        Journey that = (Journey) o;
        return Objects.equals(id, that.id) && Objects.equals(shipId, that.shipId)
            && Objects.equals(originPort, that.originPort) && Objects.equals(destinationPort, that.destinationPort)
            && Objects.equals(departureDate, that.departureDate) && Objects.equals(arrivalDate, that.arrivalDate)
            && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipId, originPort, destinationPort, departureDate, arrivalDate, path);
    }
}
