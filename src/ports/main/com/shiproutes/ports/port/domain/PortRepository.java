package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.PortId;

import java.util.Optional;
import java.util.Set;

public interface PortRepository {

    void save(Port port);

    Optional<Port> search(PortId id);

    Set<Port> searchAll();

}
