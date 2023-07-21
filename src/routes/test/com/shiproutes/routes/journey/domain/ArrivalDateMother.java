package com.shiproutes.routes.journey.domain;

import com.shiproutes.shared.domain.InstantMother;
import com.shiproutes.shared.domain.InstantValueObject;

public class ArrivalDateMother {

    public static ArrivalDate random() {
        return new ArrivalDate(InstantMother.random());
    }

    public static ArrivalDate randomBefore(InstantValueObject instant) {
        return new ArrivalDate(InstantMother.randomBefore(instant.value()));
    }

    public static ArrivalDate randomAfter(InstantValueObject instant) {
        return new ArrivalDate(InstantMother.randomAfter(instant.value()));
    }
}
