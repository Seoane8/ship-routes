package com.shiproutes.ships.ship.application.find;

import com.shiproutes.shared.domain.bus.query.Response;
import com.shiproutes.ships.ship.domain.Ship;

import java.util.Objects;

public final class ShipResponse implements Response {

    private final String imo;
    private final String name;
    private final Integer teus;

    public ShipResponse(String imo, String name, Integer teus) {
        this.imo = imo;
        this.name = name;
        this.teus = teus;
    }

    public static ShipResponse from(Ship ship) {
        return new ShipResponse(ship.imo().value(), ship.name().value(), ship.teus().value());
    }

    public String imo() {
        return imo;
    }

    public String name() {
        return name;
    }

    public Integer teus() {
        return teus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShipResponse)) return false;
        ShipResponse that = (ShipResponse) o;
        return Objects.equals(imo, that.imo) && Objects.equals(name, that.name) && Objects.equals(teus, that.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imo, name, teus);
    }
}
