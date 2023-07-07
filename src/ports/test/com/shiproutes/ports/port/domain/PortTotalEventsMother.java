package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class PortTotalEventsMother {

    public static PortTotalEvents random() {
        return new PortTotalEvents(MotherCreator.random().number().numberBetween(0L, Long.MAX_VALUE));
    }

    public static PortTotalEvents one() {
        return new PortTotalEvents(1L);
    }

}
