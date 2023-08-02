package com.shiproutes.backoffice.ship.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.IMO;

public class ShipNotExist extends DomainError {
    public ShipNotExist(IMO imo) {
        super("ship_not_exist", String.format("The ship <%s> doesn't exist", imo.value()));
    }
}
