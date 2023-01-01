package com.shiproutes.apps.ships.backend.controller;


import com.shiproutes.apps.ApplicationTestCase;
import org.springframework.transaction.annotation.Transactional;

@Transactional("ships-transaction_manager")
public abstract class ShipApplicationTestCase extends ApplicationTestCase {
}
