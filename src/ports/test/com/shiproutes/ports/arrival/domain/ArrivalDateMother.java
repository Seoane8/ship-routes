package com.shiproutes.ports.arrival.domain;

import com.shiproutes.shared.domain.InstantMother;

public final class ArrivalDateMother {

    public static ArrivalDate random() {
        return new ArrivalDate(InstantMother.random());
    }

}
