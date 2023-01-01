package com.shiproutes.ships.ship.domain;

import com.shiproutes.shared.domain.DomainError;

public final class ShipAlreadyRecorded extends DomainError {
    public ShipAlreadyRecorded(IMO imo) {
        super("ship_already_recorded", String.format("The ship <%s> is already recorded", imo.value()));
    }
}
