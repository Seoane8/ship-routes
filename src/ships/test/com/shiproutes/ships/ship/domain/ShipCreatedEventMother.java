package com.shiproutes.ships.ship.domain;

public final class ShipCreatedEventMother {

    public static ShipCreatedEvent fromShip(Ship ship) {
        return new ShipCreatedEvent(ship.imo().value(), ship.name().value(), ship.teus().value());
    }

}
