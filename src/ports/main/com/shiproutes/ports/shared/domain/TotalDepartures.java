package com.shiproutes.ports.shared.domain;

import com.shiproutes.shared.domain.LongValueObject;

public class TotalDepartures extends LongValueObject {
    public TotalDepartures(Long value) {
        super(value);
    }

    public static TotalDepartures initialize() {
        return new TotalDepartures(0L);
    }

    public TotalDepartures increment() {
        return new TotalDepartures(value() + 1);
    }
}
