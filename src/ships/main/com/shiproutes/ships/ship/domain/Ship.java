package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.AggregateRoot;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Teus;

import java.util.Objects;

public final class Ship extends AggregateRoot {
    private final IMO imo;
    private final ShipName name;
    private final Teus teus;

    public Ship(IMO imo, ShipName name, Teus teus) {
        this.imo = imo;
        this.name = name;
        this.teus = teus;
    }

    public static Ship create(IMO imo, ShipName name, Teus teus) {
        Ship ship = new Ship(imo, name, teus);

        ship.record(new ShipCreatedEvent(imo.value(), name.value(), teus.value()));

        return ship;
    }

    public IMO imo() {
        return imo;
    }

    public ShipName name() {
        return name;
    }

    public Teus teus() {
        return teus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        Ship ship = (Ship) o;
        return Objects.equals(imo, ship.imo) && Objects.equals(name, ship.name) && Objects.equals(teus, ship.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imo, name, teus);
    }
}
