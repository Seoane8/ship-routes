package com.shiproutes.apps.ports.backend.controller;

import com.shiproutes.apps.ApplicationTestCase;
import org.springframework.transaction.annotation.Transactional;

@Transactional("ports-transaction_manager")
public abstract class PortApplicationTestCase extends ApplicationTestCase {
}
