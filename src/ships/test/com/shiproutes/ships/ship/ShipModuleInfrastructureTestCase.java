package com.shiproutes.ships.ship;

import com.shiproutes.ships.ShipsContextInfrastructureTestCase;
import com.shiproutes.ships.ship.domain.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ShipModuleInfrastructureTestCase extends ShipsContextInfrastructureTestCase {
    @Autowired
    protected ShipRepository mySqlShipRepository;
}
