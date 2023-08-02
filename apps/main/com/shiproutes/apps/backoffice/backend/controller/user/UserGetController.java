package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.backoffice.user.application.authenticate.AuthenticateUserQuery;
import com.shiproutes.backoffice.user.application.authenticate.UserResponse;
import com.shiproutes.backoffice.user.domain.InvalidCredentials;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.JwtInfo;
import com.shiproutes.shared.infrastructure.auth.JwtUtils;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;

@RestController
public class UserGetController extends ApiController {

    private final JwtUtils jwtUtils;

    public UserGetController(QueryBus queryBus, CommandBus commandBus, JwtUtils jwtUtils) {
        super(queryBus, commandBus);
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/login")
    public ResponseEntity<HashMap<String, Serializable>> handle(
        @RequestParam(name = "username") String username,
        @RequestParam(name = "password") String password
    ) {
        UserResponse response = ask(new AuthenticateUserQuery(username, password));
        String token = jwtUtils.generateToken(new JwtInfo(
            response.id(),
            response.username(),
            response.email(),
            response.role()
        ));
        return ResponseEntity.ok().body(new HashMap<>() {{
            put("id", response.id());
            put("username", response.username());
            put("role", response.role());
            put("token", token);
        }});
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(InvalidCredentials.class, HttpStatus.NOT_FOUND);
        }};
    }
}
