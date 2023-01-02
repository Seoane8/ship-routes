package com.shiproutes.ports.port.domain;

public final class PortMother {


    public static Port random() {
        return new Port(
            PortIdMother.random(),
            PortNameMother.random(),
            LocodeMother.random(),
            CoordinatesMother.random());
    }
}
