package com.shiproutes.apps.routes.backend.controller.route;

import com.shiproutes.routes.route.application.search_all.AllRoutesResponse;
import com.shiproutes.routes.route.application.search_all.SearchAllRoutesQuery;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AllRoutesGetController extends ApiController {

    public AllRoutesGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/routes")
    public ResponseEntity<Set<Map<String, Serializable>>> handle() {
        AllRoutesResponse response = ask(new SearchAllRoutesQuery());

        Set<Map<String, Serializable>> routes = response.routes().stream()
            .map(route -> new HashMap<String, Serializable>() {{
                    put("id", route.id());
                    put("origin_port", route.originPort());
                    put("destination_port", route.destinationPort());
                    put("path", route.path().stream().map(ArrayList::new).collect(Collectors.toCollection(ArrayList::new)));
                    put("journeys", route.journeys());
                    put("teus", route.teus());
                }}
            ).collect(Collectors.toCollection(HashSet::new));

        return ResponseEntity.ok().body(routes);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>();
    }
}
