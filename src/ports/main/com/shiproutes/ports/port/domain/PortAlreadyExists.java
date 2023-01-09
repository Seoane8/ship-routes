package com.shiproutes.ports.port.domain;

import com.shiproutes.ports.shared.domain.PortId;
import com.shiproutes.shared.domain.DomainError;

public final class PortAlreadyExists extends DomainError {
    public PortAlreadyExists(PortId portId) {
        super("port_already_exists", String.format("The port <%s> already exists", portId));
    }
}
