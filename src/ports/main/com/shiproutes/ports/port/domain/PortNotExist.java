package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.ports.PortId;

public final class PortNotExist extends DomainError {
    public PortNotExist(PortId id) {
        super("port_not_exist", String.format("Port <%s> doesn't exist", id));
    }
}
