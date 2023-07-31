package com.shiproutes.apps.ports.backend.controller.port_event;

import com.shiproutes.ports.port_event.application.AllPortEventsBetweenDatesResponse;
import com.shiproutes.ports.port_event.application.search_between_date.SearchAllPortEventsBetweenDatesQuery;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.StartDateAfterEndDate;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PortEventsBetweenDatesGetController extends ApiController {

    public PortEventsBetweenDatesGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/events")
    public ResponseEntity<Set<Map<String, Serializable>>> handle(
        @RequestParam(name = "start_date") Instant startDate,
        @RequestParam(name = "end_date") Instant endDate
    ) {
        AllPortEventsBetweenDatesResponse response = ask(new SearchAllPortEventsBetweenDatesQuery(
            startDate,
            endDate
        ));

        Set<Map<String, Serializable>> ports = response.ports().stream()
            .map(portEvent -> new HashMap<String, Serializable>() {{
                put("port_id", portEvent.portId());
                put("port_name", portEvent.portName());
                put("longitude", portEvent.longitude());
                put("latitude", portEvent.latitude());
                put("departures", portEvent.departures());
                put("arrivals", portEvent.arrivals());
                put("teus", portEvent.teus());
            }})
            .collect(Collectors.toCollection(HashSet::new));

        return ResponseEntity.ok().body(ports);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(StartDateAfterEndDate.class, HttpStatus.BAD_REQUEST);
        }};
    }
}
