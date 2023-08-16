package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.IMO;

public class ShipNotExist extends DomainError {
    public ShipNotExist(IMO shipId) {
        super("ship_not_exist", String.format("The ship <%s> doesn't exist", shipId.value()));
    }
}
