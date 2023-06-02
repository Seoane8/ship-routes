package com.shiproutes.ports.ship.domain;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Teus;

import java.util.Objects;

public final class PortShip {

    private final IMO imo;
    private final Teus teus;

    public PortShip(IMO imo, Teus teus) {
        this.imo = imo;
        this.teus = teus;
    }

    public IMO imo() {
        return imo;
    }

    public Teus teus() {
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
