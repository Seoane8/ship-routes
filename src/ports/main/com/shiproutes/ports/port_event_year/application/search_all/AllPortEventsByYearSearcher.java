package com.shiproutes.ports.port_event_year.application.search_all;

import com.shiproutes.ports.port_event_year.application.PortEventsByYearResponse;
import com.shiproutes.ports.port_event_year.application.AllPortEventsByYearResponse;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearRepository;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AllPortEventsByYearSearcher {

    private final PortEventsByYearRepository repository;

    public AllPortEventsByYearSearcher(PortEventsByYearRepository repository) {
        this.repository = repository;
    }

    public AllPortEventsByYearResponse search() {
        Map<PortId, PortEventsByYearResponse> result = new HashMap<>();
        for (PortEventsByYear portEventsByYear : repository.searchAll()) {
            PortEventsByYearResponse port = result.computeIfAbsent(portEventsByYear.portId(),
                k -> PortEventsByYearResponse.from(portEventsByYear));
            port.addEvents(portEventsByYear);
        }
        return new AllPortEventsByYearResponse(result.values());
    }
}
