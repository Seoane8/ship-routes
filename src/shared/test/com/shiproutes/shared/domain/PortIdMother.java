package com.shiproutes.shared.domain;

public final class PortIdMother {
    public static PortId random() {
        return new PortId(UuidMother.random());
    }
}
