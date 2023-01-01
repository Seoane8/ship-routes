package com.shiproutes.ships.ship.application.find;

import com.shiproutes.shared.domain.bus.query.Query;

public final class FindShipQuery implements Query {
    private final String imo;

    public FindShipQuery(String imo) {
        this.imo = imo;
    }

    public String imo() {
        return imo;
    }
}
