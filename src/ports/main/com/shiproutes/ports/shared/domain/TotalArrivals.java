package com.shiproutes.ports.shared.domain;

import com.shiproutes.shared.domain.LongValueObject;

public class TotalArrivals extends LongValueObject {
    public TotalArrivals(Long value) {
        super(value);
    }

    public static TotalArrivals initialize() {
        return new TotalArrivals(0L);
    }

    public TotalArrivals increment() {
        return new TotalArrivals(value() + 1);
    }
}
