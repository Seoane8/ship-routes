package com.shiproutes.ports.shared.domain;

import com.shiproutes.shared.domain.LongValueObject;

public class TotalEvents extends LongValueObject {

    public TotalEvents(Long value) {
        super(value);
    }

    public static TotalEvents initialize() {
        return new TotalEvents(0L);
    }

    public TotalEvents increment() {
        return new TotalEvents(value() + 1);
    }
}
