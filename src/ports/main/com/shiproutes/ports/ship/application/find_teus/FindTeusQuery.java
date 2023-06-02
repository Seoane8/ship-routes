package com.shiproutes.ports.ship.application.find_teus;

import com.shiproutes.shared.domain.bus.query.Query;

public final class FindTeusQuery implements Query {

    private final String imo;

    public FindTeusQuery(String imo) {
        this.imo = imo;
    }

    public String imo() {
        return imo;
    }
}
