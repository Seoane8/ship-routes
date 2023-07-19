package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.ports.PortId;

public final class PortAlreadyExists extends DomainError {
    public PortAlreadyExists(PortId portId) {
        super("port_already_exists", String.format("The port <%s> already exists", portId));
    }
}
