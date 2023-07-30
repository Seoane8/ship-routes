package com.shiproutes.ingest.ship.application.find_ship;

import com.shiproutes.ingest.ship.domain.IngestShip;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Objects;

public class IngestShipResponse implements Response {

    private final String imo;

    public IngestShipResponse(String imo) {
        this.imo = imo;
    }

    public static IngestShipResponse from(IngestShip ingestShip) {
        return new IngestShipResponse(ingestShip.imo().value());
    }

    public String imo() {
        return imo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngestShipResponse)) return false;
        IngestShipResponse that = (IngestShipResponse) o;
        return Objects.equals(imo, that.imo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imo);
    }
}
