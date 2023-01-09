package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.IMO;

public final class ShipNotExists extends DomainError {

    public ShipNotExists(IMO imo) {
        super("ship_not_exists", String.format("The ship <%s> doesn't exist", imo.value()));
    }
}
