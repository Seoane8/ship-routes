package com.shiproutes.ports.port_event_month.application.search_all;

import com.shiproutes.ports.port_event_month.application.AllPortEventsByMonthResponse;
import com.shiproutes.ports.port_event_month.application.PortEventsByMonthResponse;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthRepository;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AllPortEventsByMonthSearcher {

    private final PortEventsByMonthRepository repository;

    public AllPortEventsByMonthSearcher(PortEventsByMonthRepository repository) {
        this.repository = repository;
    }

    public AllPortEventsByMonthResponse search() {
        Map<PortId, PortEventsByMonthResponse> result = new HashMap<>();
        for (PortEventsByMonth portEventsByMonth : repository.searchAll()) {
            PortEventsByMonthResponse port = result.computeIfAbsent(portEventsByMonth.portId(),
                k -> PortEventsByMonthResponse.from(portEventsByMonth));
            port.addEvents(portEventsByMonth);
        }
        return new AllPortEventsByMonthResponse(result.values());
    }
}
