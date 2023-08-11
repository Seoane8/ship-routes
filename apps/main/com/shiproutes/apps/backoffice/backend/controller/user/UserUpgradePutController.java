package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.backoffice.user.application.upgrade.UpgradeUserCommand;
import com.shiproutes.backoffice.user.application.upgrade.UserNotExist;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashMap;

public class UserUpgradePutController extends ApiController {


    public UserUpgradePutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping("/users/{id}/upgrade")
    public ResponseEntity<String> handle(@PathVariable String id) throws Exception {
        dispatch(new UpgradeUserCommand(id));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(UserNotExist.class, HttpStatus.NOT_FOUND);
        }};
    }
}
