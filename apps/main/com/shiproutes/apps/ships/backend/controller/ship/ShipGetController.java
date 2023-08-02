package com.shiproutes.apps.ships.backend.controller.ship;

import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.AuthorizeAll;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import com.shiproutes.ships.ship.application.find.FindShipQuery;
import com.shiproutes.ships.ship.application.find.ShipResponse;
import com.shiproutes.ships.ship.domain.ShipNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
@AuthorizeAll
public class ShipGetController extends ApiController {

    public ShipGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/ships/{imo}")
    public ResponseEntity<Map<String, Serializable>> handle(@PathVariable String imo){
        ShipResponse ship = ask(new FindShipQuery(imo));

        return ResponseEntity.ok().body(Map.of(
            "imo", ship.imo(),
            "name", ship.name(),
            "teus", ship.teus()
        ));
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>(){{
            put(ShipNotExists.class, HttpStatus.NOT_FOUND);
        }};
    }
}
