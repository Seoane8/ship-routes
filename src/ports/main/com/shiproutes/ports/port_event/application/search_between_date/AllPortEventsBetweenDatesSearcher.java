package com.shiproutes.ports.port_event.application.search_between_date;

import com.shiproutes.ports.port_event.application.AllPortEventsBetweenDatesResponse;
import com.shiproutes.ports.port_event.application.PortEventsBetweenDatesResponse;
import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventDate;
import com.shiproutes.ports.port_event.domain.PortEventRepository;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.StartDateAfterEndDate;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.HashMap;
import java.util.Map;

@Service
public class AllPortEventsBetweenDatesSearcher {

    private final PortEventRepository repository;

    public AllPortEventsBetweenDatesSearcher(PortEventRepository repository) {
        this.repository = repository;
    }

    public AllPortEventsBetweenDatesResponse search(PortEventDate startDate, PortEventDate endDate) {
        ensureStartDateIsBeforeEndDate(startDate, endDate);
        Map<PortId, PortEventsBetweenDatesResponse> result = new HashMap<>();
        for (PortEvent event : repository.searchBetweenDates(startDate.startOfDay(), endDate.endOfDay())) {
            PortEventsBetweenDatesResponse port = result.computeIfAbsent(event.portId(),
                k -> PortEventsBetweenDatesResponse.from(event));
            port.addEvents(event);
        }
        return new AllPortEventsBetweenDatesResponse(result.values());
    }

    private void ensureStartDateIsBeforeEndDate(PortEventDate startDate, PortEventDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new StartDateAfterEndDate(startDate, endDate);
        }
    }
}
