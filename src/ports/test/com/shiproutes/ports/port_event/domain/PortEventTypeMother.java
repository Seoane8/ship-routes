package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class PortEventTypeMother {

    public static PortEventType random() {
        return MotherCreator.random().options().option(PortEventType.class);
    }
}
