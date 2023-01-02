package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.MotherCreator;

public final class CoordinatesMother {
    public static Coordinates random() {
        return new Coordinates(
            new Latitude(MotherCreator.random().number().randomDouble(6, -90, 90)),
            new Longitude(MotherCreator.random().number().randomDouble(4, -180, 180))
        );
    }
}
