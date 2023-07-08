package com.shiproutes.ports.shared.domain;

import com.shiproutes.shared.domain.MotherCreator;

public class TotalEventsMother {

    public static TotalEvents random() {
        return new TotalEvents(MotherCreator.random().number().numberBetween(0L, Long.MAX_VALUE));
    }

    public static TotalEvents one() {
        return new TotalEvents(1L);
    }

}
