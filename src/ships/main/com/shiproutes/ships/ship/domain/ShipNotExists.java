package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.DomainError;

public final class ShipNotExists extends DomainError {

    public ShipNotExists(IMO imo) {
        super("ship_not_exists", String.format("The ship <%s> doesn't exists", imo.value()));
    }
}
