package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.backoffice.user.application.create.CreateUserCommand;
import com.shiproutes.backoffice.user.domain.UserAlreadyExists;
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
public class UserPostController extends ApiController {

    public UserPostController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping("/users")
    public ResponseEntity<String> handle(@RequestBody CreateUserCommand command) {
        dispatch(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(UserAlreadyExists.class, HttpStatus.CONFLICT);
        }};
    }
}
