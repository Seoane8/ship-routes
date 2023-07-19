package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.ports.PortId;

public class PortNotExist extends DomainError {
    public PortNotExist(PortId portId) {
        super("port_not_exist", String.format("The port <%s> does not exist", portId));
    }
}
