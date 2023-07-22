package com.shiproutes.routes.journey.domain;

import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Objects;

public class Journey {

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
        return new Journey(id, shipId, originPort, destinationPort, departureDate, arrivalDate, path);
    }

    public static Journey departure(JourneyId journeyId, IMO shipId, PortId originPort, DepartureDate departureDate) {
        return new Journey(journeyId, shipId, originPort, PortId.empty(), departureDate, ArrivalDate.empty(),
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journey)) return false;
        Journey journey = (Journey) o;
        return Objects.equals(id, journey.id) && Objects.equals(shipId, journey.shipId)
            && Objects.equals(originPort, journey.originPort)
            && Objects.equals(destinationPort, journey.destinationPort)
            && Objects.equals(departureDate, journey.departureDate) && Objects.equals(arrivalDate, journey.arrivalDate)
            && Objects.equals(path, journey.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shipId, originPort, destinationPort, departureDate, arrivalDate, path);
    }
}
