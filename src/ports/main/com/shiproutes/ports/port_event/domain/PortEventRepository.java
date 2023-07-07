package com.shiproutes.ports.port_event.domain;

import java.util.Optional;

public interface PortEventRepository {

    void save(PortEvent portEvent);

    Optional<PortEvent> search(PortEventId id);
}
