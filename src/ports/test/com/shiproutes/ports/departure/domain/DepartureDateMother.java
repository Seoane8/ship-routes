package com.shiproutes.ports.departure.domain;

import com.shiproutes.shared.domain.InstantMother;

public final class DepartureDateMother {

    public static DepartureDate random() {
        return new DepartureDate(InstantMother.random());
    }
}
