package com.shiproutes.apps.ports.backend.controller.port;

import com.shiproutes.ports.port.application.create.CreatePortCommand;
import com.shiproutes.ports.port.domain.PortAlreadyExists;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.AuthorizeAdmins;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@AuthorizeAdmins
public final class PortPostController extends ApiController {

    public PortPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("/ports")
    public ResponseEntity<String> handle(@RequestBody CreatePortCommand command) {
        dispatch(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(PortAlreadyExists.class, HttpStatus.CONFLICT);
        }};
    }
}
