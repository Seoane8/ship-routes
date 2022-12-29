package com.shiproutes.apps.ship;

import com.shiproutes.apps.ApplicationTestCase;
import org.springframework.transaction.annotation.Transactional;

@Transactional("ship-transaction_manager")
public abstract class ShipApplicationTestCase extends ApplicationTestCase {
}
