package com.shiproutes.ports.port_event.domain;

import com.shiproutes.ports.shared.domain.CoordinatesMother;
import com.shiproutes.ports.shared.domain.PortIdMother;
import com.shiproutes.shared.domain.IMOMother;
import com.shiproutes.shared.domain.TeusMother;

public final class PortEventMother {

    public static PortEvent random() {
        return new PortEvent(
            PortEventIdMother.random(),
            PortEventTypeMother.random(),
            PortIdMother.random(),
            CoordinatesMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortEventDateMother.random()
        );
    }

}
