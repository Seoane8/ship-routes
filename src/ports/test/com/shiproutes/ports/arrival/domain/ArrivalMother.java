package com.shiproutes.ports.arrival.domain;

import com.shiproutes.ports.shared.domain.PortIdMother;
import com.shiproutes.shared.domain.IMOMother;

class ArrivalMother {

    public static Arrival random() {
        return new Arrival(
            ArrivalIdMother.random(),
            PortIdMother.random(),
            IMOMother.random(),
            ArrivalDateMother.random()
        );
    }

}
