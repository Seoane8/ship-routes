package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.WordMother;

public final class ShipNameMother {

    public static ShipName random() {
        return new ShipName(WordMother.random());
    }
}
