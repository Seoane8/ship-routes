package com.shiproutes.ports.arrival.domain;

import com.shiproutes.ports.shared.domain.CoordinatesMother;
import com.shiproutes.ports.shared.domain.PortIdMother;
import com.shiproutes.shared.domain.IMOMother;
import com.shiproutes.shared.domain.TeusMother;

public final class ArrivalMother {

    public static Arrival random() {
        return new Arrival(
            ArrivalIdMother.random(),
            PortIdMother.random(),
            CoordinatesMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            ArrivalDateMother.random()
        );
    }

}
