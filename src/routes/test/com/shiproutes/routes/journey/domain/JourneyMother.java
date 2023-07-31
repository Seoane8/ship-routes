package com.shiproutes.routes.journey.domain;

import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.routes.shared.domain.RoutePathMother;
import com.shiproutes.shared.domain.IMOMother;
import com.shiproutes.shared.domain.TeusMother;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class JourneyMother {

    public static Journey random() {
        DepartureDate departureDate = DepartureDateMother.random();
        return new Journey(
            JourneyIdMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            departureDate,
            ArrivalDateMother.randomAfter(departureDate),
            RoutePathMother.random()
        );
    }

    public static Journey randomDeparture() {
        return new Journey(
            JourneyIdMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortIdMother.random(),
            PortId.empty(),
            DepartureDateMother.random(),
            ArrivalDate.empty(),
            RoutePath.empty()
        );
    }

    public static Journey randomArrival() {
        return new Journey(
            JourneyIdMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortId.empty(),
            PortIdMother.random(),
            DepartureDate.empty(),
            ArrivalDateMother.random(),
            RoutePath.empty()
        );
    }

    public static Journey incompleteMatchOfDeparture(Journey journey) {
        return new Journey(
            JourneyIdMother.random(),
            journey.shipId(),
            journey.teus(),
            PortId.empty(),
            journey.destinationPort(),
            DepartureDate.empty(),
            journey.arrivalDate(),
            RoutePath.empty()
        );
    }

    public static Journey incompleteMatchOfArrival(Journey journey) {
        return new Journey(
            JourneyIdMother.random(),
            journey.shipId(),
            journey.teus(),
            journey.originPort(),
            PortId.empty(),
            journey.departureDate(),
            ArrivalDate.empty(),
            RoutePath.empty()
        );
    }

    public static Journey completeMatchOfDeparture(Journey journey) {
        return new Journey(
            JourneyIdMother.random(),
            journey.shipId(),
            journey.teus(),
            PortIdMother.random(),
            journey.destinationPort(),
            DepartureDateMother.randomBefore(journey.departureDate()),
            journey.arrivalDate(),
            RoutePathMother.random()
        );
    }

    public static Journey completeMatchOfArrival(Journey journey) {
        return new Journey(
            JourneyIdMother.random(),
            journey.shipId(),
            journey.teus(),
            journey.originPort(),
            PortIdMother.random(),
            journey.departureDate(),
            ArrivalDateMother.randomAfter(journey.arrivalDate()),
            RoutePathMother.random()
        );
    }

    public static Journey incompleteUnmatchOfDeparture(Journey journey) {
        return new Journey(
            JourneyIdMother.random(),
            journey.shipId(),
            journey.teus(),
            PortId.empty(),
            PortIdMother.random(),
            DepartureDate.empty(),
            ArrivalDateMother.randomBefore(journey.departureDate()),
            RoutePath.empty()
        );
    }

    public static Journey incompleteUnmatchOfArrival(Journey journey) {
        return new Journey(
            JourneyIdMother.random(),
            journey.shipId(),
            journey.teus(),
            PortIdMother.random(),
            PortId.empty(),
            DepartureDateMother.randomAfter(journey.arrivalDate()),
            ArrivalDate.empty(),
            RoutePath.empty()
        );
    }

    public static Journey completeUnmatchOfDeparture(Journey journey) {
        return new Journey(
            JourneyIdMother.random(),
            journey.shipId(),
            journey.teus(),
            PortIdMother.random(),
            PortIdMother.random(),
            DepartureDateMother.randomAfter(journey.departureDate()),
            ArrivalDateMother.randomAfter(journey.departureDate()),
            RoutePathMother.random()
        );
    }

    public static Journey completeUnmatchOfArrival(Journey journey) {
        return new Journey(
            JourneyIdMother.random(),
            journey.shipId(),
            journey.teus(),
            PortIdMother.random(),
            PortIdMother.random(),
            DepartureDateMother.randomBefore(journey.arrivalDate()),
            ArrivalDateMother.randomBefore(journey.arrivalDate()),
            RoutePathMother.random()
        );
    }

    public static Journey randomWithDepartureDateBetween(DepartureDate startDate, DepartureDate endDate) {
        return new Journey(
            JourneyIdMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            DepartureDateMother.randomBetween(startDate, endDate),
            ArrivalDateMother.randomAfter(endDate),
            RoutePathMother.random()
        );
    }

    public static Journey randomDepartureBefore(DepartureDate date) {
        return new Journey(
            JourneyIdMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            DepartureDateMother.randomBefore(date),
            ArrivalDateMother.randomAfter(date),
            RoutePathMother.random()
        );
    }
}
