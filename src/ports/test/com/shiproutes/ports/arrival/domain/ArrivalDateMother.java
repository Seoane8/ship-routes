package com.shiproutes.ports.arrival.domain;

import com.shiproutes.shared.domain.MotherCreator;

import java.util.concurrent.TimeUnit;

class ArrivalDateMother {

    public static ArrivalDate random() {
        return new ArrivalDate(MotherCreator.random().date().past(2000, TimeUnit.DAYS).toInstant());
    }

}
