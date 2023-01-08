package com.shiproutes.ports.arrival.domain;

import com.shiproutes.shared.domain.UuidMother;

class ArrivalIdMother {

    public static ArrivalId random() {
        return new ArrivalId(UuidMother.random());
    }

}
