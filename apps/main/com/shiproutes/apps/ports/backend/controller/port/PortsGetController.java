package com.shiproutes.apps.ports.backend.controller.port;

import com.shiproutes.ports.port.application.PortsResponse;
import com.shiproutes.ports.port.application.search_all.SearchAllPortsQuery;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public final class PortsGetController extends ApiController {

    public PortsGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/ports")
    public ResponseEntity<Set<Map<String, ? extends Serializable>>> handle() {
        PortsResponse response = ask(new SearchAllPortsQuery());

        Set<Map<String, ? extends Serializable>> ports = response.ports().stream()
            .map(port -> Map.of(
                "id", port.id(),
                "name", port.name(),
                "locode", port.locode(),
                "longitude", port.longitude(),
                "latitude", port.latitude(),
                "total_departures", port.totalDepartures()
            )).collect(Collectors.toSet());

        return ResponseEntity.ok().body(ports);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>();
    }
}
