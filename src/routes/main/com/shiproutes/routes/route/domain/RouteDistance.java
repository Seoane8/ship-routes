package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.IntValueObject;

public final class RouteDistance extends IntValueObject {

    public RouteDistance(Integer value) {
        super(value);
        ensureIsPositive(value);
    }

    private void ensureIsPositive(Integer value) {
        if (value < 1) throw new IllegalArgumentException("Route distance must be positive");
    }
}
