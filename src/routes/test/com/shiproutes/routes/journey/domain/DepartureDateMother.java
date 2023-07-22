package com.shiproutes.routes.journey.domain;

import com.shiproutes.shared.domain.InstantMother;
import com.shiproutes.shared.domain.InstantValueObject;

public class DepartureDateMother {

    public static DepartureDate random() {
        return new DepartureDate(InstantMother.random());
    }

    public static DepartureDate randomBefore(InstantValueObject instant) {
        return new DepartureDate(InstantMother.randomBefore(instant.value()));
    }

    public static DepartureDate randomAfter(InstantValueObject instant) {
        return new DepartureDate(InstantMother.randomAfter(instant.value()));
    }

    public static DepartureDate randomBetween(InstantValueObject startDate, InstantValueObject endDate) {
        return new DepartureDate(InstantMother.randomBetween(startDate.value(), endDate.value()));
    }
}
