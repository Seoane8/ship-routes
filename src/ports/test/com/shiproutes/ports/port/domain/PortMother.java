package com.shiproutes.ports.port.domain;

import com.shiproutes.ports.shared.domain.CoordinatesMother;
import com.shiproutes.ports.shared.domain.PortIdMother;

public final class PortMother {

    public static Port randomNew() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            PortTotalDepartures.initialize()
        );
    }

    public static Port random() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random(),
            PortTotalDeparturesMother.random()
        );
    }



}
