package com.shiproutes.ports.port_event.domain;

import java.util.Optional;
import java.util.Set;

public interface PortEventRepository {

    void save(PortEvent portEvent);

    Optional<PortEvent> search(PortEventId id);

    Set<PortEvent> searchBetweenDates(PortEventDate startDate, PortEventDate endDate);
}
