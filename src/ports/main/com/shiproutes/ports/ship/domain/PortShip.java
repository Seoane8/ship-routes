package com.shiproutes.ports.ship.domain;

import com.shiproutes.shared.domain.IMO;

import java.util.Objects;

public final class PortShip {

    private final IMO imo;
    private final Integer teus;

    public PortShip(IMO imo, Integer teus) {
        ensureTeusIsGreaterThenZero(teus);
        this.imo = imo;
        this.teus = teus;
    }

    private void ensureTeusIsGreaterThenZero(Integer value) {
        if (value < 0) throw new IllegalArgumentException("Teus must be positive");
    }

    public IMO imo() {
        return imo;
    }

    public Integer teus() {
        return teus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PortShip)) return false;
        PortShip portShip = (PortShip) o;
        return Objects.equals(imo, portShip.imo) && Objects.equals(teus, portShip.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imo, teus);
    }
}
