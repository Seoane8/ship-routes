package com.shiproutes.apps.ports.backend.controller.port_event;

import com.shiproutes.ports.port_event_year.application.AllPortEventsByYearResponse;
import com.shiproutes.ports.port_event_year.application.search_all.SearchAllPortEventsByYearQuery;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.AuthorizeAll;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AuthorizeAll
public class PortEventsByYearsGetController extends ApiController {
    public PortEventsByYearsGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/event/years")
    public ResponseEntity<Set<Map<String, Serializable>>> handle() {
        AllPortEventsByYearResponse response = ask(new SearchAllPortEventsByYearQuery());

        Set<Map<String, Serializable>> ports = response.ports().stream()
            .map(portEvent -> Map.of(
                "port_id", portEvent.portId(),
                "port_name", portEvent.portName(),
                "longitude", portEvent.longitude(),
                "latitude", portEvent.latitude(),
                "events", portEvent.events().stream().map(event -> new HashMap<String, Serializable>() {{
                        put("year", event.year());
                        put("departures", event.departures());
                        put("arrivals", event.arrivals());
                        put("teus", event.teus());
                    }}
                ).collect(Collectors.toCollection(HashSet::new))
            )).collect(Collectors.toCollection(HashSet::new));

        return ResponseEntity.ok().body(ports);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
