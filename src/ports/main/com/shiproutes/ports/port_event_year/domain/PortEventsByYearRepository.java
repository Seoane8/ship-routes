package com.shiproutes.ports.port_event_year.domain;

import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;
import java.util.Set;

public interface PortEventsByYearRepository {

    void save(PortEventsByYear portEventsByYear);

    Set<PortEventsByYear> searchAll();

    Optional<PortEventsByYear> search(PortId portId, Year year);

}
