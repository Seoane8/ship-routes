package com.shiproutes.ingest.port.domain;

import com.shiproutes.shared.domain.DomainError;

public class PortNotExist extends DomainError {

    public PortNotExist(String locode) {
        super("port_not_exist", String.format("The port with locode <%s> doesn't exist", locode));
    }
}
