package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.InstantValueObject;

import java.time.Instant;

public final class PortEventDate extends InstantValueObject {

    public PortEventDate(Instant value) {
        super(value);
    }
}
