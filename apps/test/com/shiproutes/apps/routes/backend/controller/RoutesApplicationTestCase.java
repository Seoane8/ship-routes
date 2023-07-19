package com.shiproutes.apps.routes.backend.controller;

import com.shiproutes.apps.ApplicationTestCase;
import org.springframework.transaction.annotation.Transactional;

@Transactional("routes-transaction_manager")
public abstract class RoutesApplicationTestCase extends ApplicationTestCase {
    protected void givenThenIsAExistentRoute(String route) throws Exception {
        assertRequestWithBody("POST", "/routes", route, 201);
    }
}
