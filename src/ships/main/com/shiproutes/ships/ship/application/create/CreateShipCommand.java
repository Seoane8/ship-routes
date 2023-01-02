package com.shiproutes.ships.ship.application.create;

import com.shiproutes.shared.domain.bus.command.Command;

public final class CreateShipCommand implements Command {
    private final String imo;
    private final String name;
    private final Integer teus;

    public CreateShipCommand(String imo, String name, Integer teus) {
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
}
