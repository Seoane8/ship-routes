package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.LongValueObject;

public final class PortTotalDepartures extends LongValueObject {

    public PortTotalDepartures(Long value) {
        super(value);
    }

    public static PortTotalDepartures initialize() {
        return new PortTotalDepartures(0L);
    }

    public PortTotalDepartures increment() {
        return new PortTotalDepartures(value() + 1);
    }
}
