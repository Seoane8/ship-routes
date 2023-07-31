package com.shiproutes.apps.routes.backend.controller.journey;

import com.shiproutes.routes.journey.application.search_between_date.AllJourneysBetweenDatesResponse;
import com.shiproutes.routes.journey.application.search_between_date.SearchAllJourneysBetweenDatesQuery;
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
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class JourneysBetweenDatesGetController extends ApiController {

    public JourneysBetweenDatesGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/journeys")
    public ResponseEntity<Set<Map<String, Serializable>>> handle(
        @RequestParam(name = "start_date") Instant startDate,
        @RequestParam(name = "end_date") Instant endDate
    ) {
        AllJourneysBetweenDatesResponse response = ask(new SearchAllJourneysBetweenDatesQuery(
            startDate,
            endDate
        ));

        Set<Map<String, Serializable>> ports = response.journeys().stream()
            .map(journey -> new HashMap<String, Serializable>() {{
                put("origin_port", journey.originPort());
                put("destination_port", journey.destinationPort());
                put("path", journey.path().stream()
                    .map(ArrayList::new)
                    .collect(Collectors.toCollection(ArrayList::new))
                );
                put("journeys", journey.journeys());
                put("teus", journey.teus());
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
