package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.MotherCreator;

class PortTotalDeparturesMother {

    public static PortTotalDepartures random() {
        return new PortTotalDepartures(MotherCreator.random().number().numberBetween(0L, Long.MAX_VALUE));
    }

    public static PortTotalDepartures one() {
        return new PortTotalDepartures(1L);
    }

}
