package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.MotherCreator;

public final class TeusMother {

    public static Teus random() {
        return new Teus(MotherCreator.random().number().numberBetween(0, Integer.MAX_VALUE));
    }
}
