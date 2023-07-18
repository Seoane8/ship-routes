package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.InstantValueObject;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public final class PortEventDate extends InstantValueObject {

    public PortEventDate(Instant value) {
        super(value);
    }

    public PortEventDate startOfDay() {
        return new PortEventDate(this.value
            .truncatedTo(ChronoUnit.DAYS)
        );
    }

    public PortEventDate endOfDay() {
        return new PortEventDate(this.value
            .truncatedTo(ChronoUnit.DAYS)
            .plus(1, ChronoUnit.DAYS)
            .minus(1, ChronoUnit.MILLIS)
        );
    }
}
