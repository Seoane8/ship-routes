package com.shiproutes.ports.port.domain;

import com.shiproutes.ports.shared.domain.PortIdMother;

public final class PortMother {


    public static Port random() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random());
    }
}
