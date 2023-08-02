package com.shiproutes.backoffice.ship.domain;

import com.shiproutes.shared.domain.IMO;

import java.util.Objects;

public final class IngestShip {

    private final IMO imo;

    public IngestShip(IMO imo) {
        this.imo = imo;
    }

    public IMO imo() {
        return imo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngestShip)) return false;
        IngestShip ingestShip = (IngestShip) o;
        return Objects.equals(imo, ingestShip.imo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imo);
    }
}
