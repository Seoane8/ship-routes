package com.shiproutes.apps.routes.backend.controller.route;

import com.shiproutes.routes.route.application.create.CreateRouteCommand;
import com.shiproutes.routes.route.domain.ExistentRouteMismatch;
import com.shiproutes.routes.route.domain.PortNotExist;
import com.shiproutes.routes.route.domain.RouteAlreadyExist;
import com.shiproutes.routes.route.domain.RoutePathMismatch;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.AuthorizeAdmins;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@AuthorizeAdmins
public class RoutePutController extends ApiController {
    public RoutePutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping("/routes/{id}")
    public ResponseEntity<String> handle(@PathVariable String id, @RequestBody Body body) {
        dispatch(new CreateRouteCommand(id, body.origin(), body.destination(), body.path()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(RoutePathMismatch.class, HttpStatus.BAD_REQUEST);
            put(PortNotExist.class, HttpStatus.BAD_REQUEST);
            put(ExistentRouteMismatch.class, HttpStatus.BAD_REQUEST);
            put(RouteAlreadyExist.class, HttpStatus.CONFLICT);
        }};
    }

    public static final class Body {
        private final String origin;
        private final String destination;
        private final List<List<Double>> path;

        public Body(String origin, String destination, List<List<Double>> path) {
            this.origin = origin;
            this.destination = destination;
            this.path = path;
        }

        public String origin() {
            return origin;
        }

        public String destination() {
            return destination;
        }

        public List<List<Double>> path() {
            return path;
        }
    }
}
