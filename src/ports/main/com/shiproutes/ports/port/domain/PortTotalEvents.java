package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.LongValueObject;

public final class PortTotalEvents extends LongValueObject {

    public PortTotalEvents(Long value) {
        super(value);
    }

    public static PortTotalEvents initialize() {
        return new PortTotalEvents(0L);
    }

    public PortTotalEvents increment() {
        return new PortTotalEvents(value() + 1);
    }
}
