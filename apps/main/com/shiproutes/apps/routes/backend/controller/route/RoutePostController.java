package com.shiproutes.apps.routes.backend.controller.route;

import com.shiproutes.routes.route.application.create.CreateRouteCommand;
import com.shiproutes.routes.route.domain.PortNotExist;
import com.shiproutes.routes.route.domain.RouteAlreadyExists;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RoutePostController extends ApiController {
    public RoutePostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("/routes")
    public ResponseEntity<String> handle(@RequestBody CreateRouteCommand command) {
        dispatch(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(RouteAlreadyExists.class, HttpStatus.CONFLICT);
            put(PortNotExist.class, HttpStatus.BAD_REQUEST);
        }};
    }
}
