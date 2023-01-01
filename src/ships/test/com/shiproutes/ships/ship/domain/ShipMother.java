package com.shiproutes.ships.ship.domain;

public final class ShipMother {

    public static Ship random() {
        return new Ship(IMOMother.random(), ShipNameMother.random(), TeusMother.random());
    }
}
