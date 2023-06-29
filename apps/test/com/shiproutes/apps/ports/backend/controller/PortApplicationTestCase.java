package com.shiproutes.apps.ports.backend.controller;

import com.shiproutes.apps.ApplicationTestCase;
import org.springframework.transaction.annotation.Transactional;

@Transactional("ports-transaction_manager")
public abstract class PortApplicationTestCase extends ApplicationTestCase {
    protected void givenThenIsAExistentPort(String port) throws Exception {
        assertRequestWithBody("POST", "/ports", port, 201);
    }
}
