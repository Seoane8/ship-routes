package com.shiproutes.shared.domain.ports;

import com.shiproutes.shared.domain.UuidMother;

public final class PortIdMother {
    public static PortId random() {
        return new PortId(UuidMother.random());
    }
}
