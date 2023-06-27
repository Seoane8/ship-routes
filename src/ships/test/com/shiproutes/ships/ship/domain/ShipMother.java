package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.IMOMother;
import com.shiproutes.shared.domain.TeusMother;

public final class ShipMother {

    public static Ship random() {
        return new Ship(IMOMother.random(), ShipNameMother.random(), TeusMother.random());
    }
}
