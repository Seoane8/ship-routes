package com.shiproutes.ports.year_port_event.application.search_all;

import com.shiproutes.ports.year_port_event.application.PortEventsByYearResponse;
import com.shiproutes.ports.year_port_event.application.AllPortEventsByYearResponse;
import com.shiproutes.ports.year_port_event.domain.YearPortEvent;
import com.shiproutes.ports.year_port_event.domain.YearPortEventRepository;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AllYearPortEventsSearcher {

    private final YearPortEventRepository repository;

    public AllYearPortEventsSearcher(YearPortEventRepository repository) {
        this.repository = repository;
    }

    public AllPortEventsByYearResponse search() {
        Map<PortId, PortEventsByYearResponse> result = new HashMap<>();
        for (YearPortEvent yearPortEvent : repository.searchAll()) {
            PortEventsByYearResponse element = result.computeIfAbsent(yearPortEvent.portId(),
                k -> PortEventsByYearResponse.from(yearPortEvent));
            element.addEvents(yearPortEvent);
        }
        return new AllPortEventsByYearResponse(result.values());
    }
}
