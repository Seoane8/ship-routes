package com.shiproutes.ports.departure.domain;

import com.shiproutes.shared.domain.UuidMother;

public final class DepartureIdMother {

    public static DepartureId random() {
        return new DepartureId(UuidMother.random());
    }
}
