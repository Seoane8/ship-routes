package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.InstantMother;

public final class PortEventDateMother {

    public static PortEventDate random() {
        return new PortEventDate(InstantMother.random());
    }

}
