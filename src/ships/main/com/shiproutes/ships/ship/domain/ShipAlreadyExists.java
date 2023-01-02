package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.DomainError;

public final class ShipAlreadyExists extends DomainError {
    public ShipAlreadyExists(IMO imo) {
        super("ship_already_exists", String.format("The ship <%s> is already exists", imo.value()));
    }
}
