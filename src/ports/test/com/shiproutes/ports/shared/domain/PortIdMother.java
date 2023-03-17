package com.shiproutes.ports.shared.domain;

import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.UuidMother;

public final class PortIdMother {
    public static PortId random() {
        return new PortId(UuidMother.random());
    }
}
