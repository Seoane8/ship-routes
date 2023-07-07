package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.UuidMother;

public final class PortEventIdMother {

    public static PortEventId random() {
        return new PortEventId(UuidMother.random());
    }

}
