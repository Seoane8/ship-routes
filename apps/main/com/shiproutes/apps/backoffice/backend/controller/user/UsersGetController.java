package com.shiproutes.apps.backoffice.backend.controller.user;

import com.shiproutes.backoffice.user.application.search.SearchUserQuery;
import com.shiproutes.backoffice.user.application.search.UsersResponse;
import com.shiproutes.shared.domain.DomainError;
import com.shiproutes.shared.domain.bus.command.CommandBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.infrastructure.auth.AuthorizeAdmins;
import com.shiproutes.shared.infrastructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

@RestController
@AuthorizeAdmins
public class UsersGetController extends ApiController {
    public UsersGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/users")
    public ResponseEntity<HashSet<HashMap<String, Serializable>>> handle(
        @RequestParam(name = "username") String username
    ) {
        UsersResponse users = ask(new SearchUserQuery(username));
        HashSet<HashMap<String, Serializable>> result = users.users().stream()
            .map(user -> new HashMap<String, Serializable>() {{
                put("id", user.id());
                put("username", user.username());
                put("email", user.email());
                put("role", user.role());
            }}).collect(Collectors.toCollection(HashSet::new));
        return ResponseEntity.ok().body(result);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<>();
    }
}
