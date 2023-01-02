package com.shiproutes.apps.ships.backend.controller.ship;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import com.shiproutes.ships.ship.application.record.RecordShipCommand;
import com.shiproutes.ships.ship.domain.ShipAlreadyRecorded;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class ShipPostController extends ApiController {

    public ShipPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(value = "/ships")
    public ResponseEntity<String> handle(@RequestBody RecordShipCommand command) {
        dispatch(command);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(ShipAlreadyRecorded.class, HttpStatus.CONFLICT);
        }};
    }
}
