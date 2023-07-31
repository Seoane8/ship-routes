package com.shiproutes.apps.routes.backend.controller.journey;

import com.shiproutes.routes.journey_month.application.search_all.AllJourneysByMonthResponse;
import com.shiproutes.routes.journey_month.application.search_all.SearchAllJourneysByMonthQuery;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class JourneysByMonthsGetController extends ApiController {
    public JourneysByMonthsGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/journeys/months")
    public ResponseEntity<Set<Map<String, Object>>> handle() {
        AllJourneysByMonthResponse response = ask(new SearchAllJourneysByMonthQuery());

        Set<Map<String, Object>> journeys = response.journeys().stream()
            .map(journey -> Map.of(
                "origin_port", journey.originPort(),
                "destination_port", journey.destinationPort(),
                "path", journey.path(),
                "journeys", journey.journeys().stream().map(j -> new HashMap<String, Object>() {{
                        put("month", j.month());
                        put("year", j.year());
                        put("total", j.journeys());
                        put("teus", j.teus());
                    }}
                ).collect(Collectors.toCollection(HashSet::new))
            )).collect(Collectors.toCollection(HashSet::new));

        return ResponseEntity.ok().body(journeys);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>();
    }
}
