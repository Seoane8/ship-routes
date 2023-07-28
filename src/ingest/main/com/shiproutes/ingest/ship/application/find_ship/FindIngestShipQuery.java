package com.shiproutes.ingest.ship.application.find_ship;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public class FindIngestShipQuery implements Query {

    private final String imo;

    public FindIngestShipQuery(String imo) {
        this.imo = imo;
    }

    public String imo() {
        return imo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FindIngestShipQuery)) return false;
        FindIngestShipQuery that = (FindIngestShipQuery) o;
        return Objects.equals(imo, that.imo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imo);
    }
}
