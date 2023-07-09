package com.shiproutes.ports.port_event_month.domain;

import com.shiproutes.ports.port_event_year.domain.Year;
import com.shiproutes.shared.domain.PortId;

import java.util.Optional;
import java.util.Set;

public interface PortEventsByMonthRepository {

    void save(PortEventsByMonth portEventsByMonth);

    Set<PortEventsByMonth> searchAll();

    Optional<PortEventsByMonth> search(PortId portId, Year year, Month month);

}
