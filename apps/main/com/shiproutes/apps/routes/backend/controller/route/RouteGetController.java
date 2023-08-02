package com.shiproutes.apps.routes.backend.controller.route;

import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.routes.shared.application.FindRoutePathQuery;
import com.shiproutes.routes.shared.application.RoutePathResponse;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.AuthorizeAll;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@AuthorizeAll
public class RouteGetController extends ApiController {

    public RouteGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/routes/path")
    public ResponseEntity<List<List<Double>>> handle(
        @RequestParam(name = "origin_port") String originPort,
        @RequestParam(name = "destination_port") String destinationPort
    ) {
        RoutePathResponse response = ask(new FindRoutePathQuery(originPort, destinationPort));
        return ResponseEntity.ok().body(response.path());
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(PortNotExist.class, HttpStatus.BAD_REQUEST);
        }};
    }
}
