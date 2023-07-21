package com.shiproutes.routes.journey.domain;

import com.shiproutes.routes.shared.domain.RoutePathMother;
import com.shiproutes.shared.domain.IMOMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class JourneyMother {

    public static Journey random() {
        DepartureDate departureDate = DepartureDateMother.random();
        return new Journey(
            JourneyIdMother.random(),
            IMOMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            departureDate,
            ArrivalDateMother.randomBefore(departureDate),
            RoutePathMother.random()
        );
    }
}
