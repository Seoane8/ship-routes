package com.shiproutes.routes.port.domain;

import com.shiproutes.shared.domain.ports.CoordinatesMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class RoutePortMother {

    public static RoutePort random() {
        return new RoutePort(
            PortIdMother.random(),
            CoordinatesMother.random()
        );
    }
}
