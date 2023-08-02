package com.shiproutes.backoffice.port_event.domain;

import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;

public interface PortCreator {

    void create(PortId id, String locode, String portName, Coordinates coordinates);

}
