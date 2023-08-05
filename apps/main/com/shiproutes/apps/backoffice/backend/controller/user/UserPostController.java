package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.backoffice.user.application.create.CreateUserCommand;
import com.shiproutes.backoffice.user.domain.UserAlreadyExists;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.UserRole;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.JwtInfo;
import com.shiproutes.shared.infrastructure.auth.JwtUtils;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;

@RestController
public class UserPostController extends ApiController {

    private final JwtUtils jwtUtils;

    public UserPostController(QueryBus queryBus, CommandBus commandBus, JwtUtils jwtUtils) {
        super(queryBus, commandBus);
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/users")
    public ResponseEntity<HashMap<String, Serializable>> handle(@RequestBody CreateUserCommand command) {
        dispatch(command);
        String token = jwtUtils.generateToken(new JwtInfo(
            command.id(),
            command.username(),
            command.email(),
            UserRole.USER.name()
        ));
        return ResponseEntity.ok().body(new HashMap<>() {{
            put("id", command.id());
            put("username", command.username());
            put("email", command.email());
            put("role", UserRole.USER.name());
            put("token", token);
        }});
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(UserAlreadyExists.class, HttpStatus.CONFLICT);
        }};
    }
}
