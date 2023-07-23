package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.shared.domain.LongValueObject;

public class JourneysCounter extends LongValueObject {

    public JourneysCounter(Long value) {
        super(value);
    }

    public static JourneysCounter initialize() {
        return new JourneysCounter(0L);
    }

    public JourneysCounter increment() {
        return new JourneysCounter(this.value() + 1);
    }

    public JourneysCounter decrement() {
        return new JourneysCounter(this.value() - 1);
    }
}
