package com.shiproutes.backoffice.ship.application.ingest;

import com.shiproutes.shared.domain.bus.command.Command;

import java.util.Objects;

public class IngestShipCommand implements Command {
    private final String imo;
    private final String name;
    private final Integer teus;

    public IngestShipCommand(String imo, String name, Integer teus) {
        this.imo = imo;
        this.name = name;
        this.teus = teus;
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
        if (!(o instanceof IngestShipCommand)) return false;
        IngestShipCommand that = (IngestShipCommand) o;
        return Objects.equals(imo, that.imo) && Objects.equals(name, that.name) && Objects.equals(teus, that.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imo, name, teus);
    }
}
