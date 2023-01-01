package com.shiproutes.ships.ship.domain;

public final class ShipRecordedMother {

    public static ShipRecorded fromShip(Ship ship) {
        return new ShipRecorded(ship.imo().value(), ship.name().value(), ship.teus().value());
    }

}
