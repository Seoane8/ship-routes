package com.shiproutes.apps.ships.backend.controller.ship;

import com.shiproutes.apps.ships.backend.controller.ShipApplicationTestCase;
import org.junit.jupiter.api.Test;

class ShipPostControllerShould extends ShipApplicationTestCase {

    @Test
    void create_a_valid_ship() throws Exception {
        assertRequestWithBody(
            "POST", "/ships",
            "{\"imo\": \"7052363\", \"name\": \"ASTRON\", \"teus\": 113}",
            201
        );
    }

    @Test
    void fail_when_ship_is_invalid() throws Exception {
        assertRequestWithBody(
            "POST", "/ships",
            "{\"imo\": \"7052365\", \"name\": \"ASTRON\", \"teus\": 113}",
            400
        );
    }

    @Test
    void fail_when_ship_already_exists() throws Exception {
        String ship = "{\"imo\": \"7052363\", \"name\": \"ASTRON\", \"teus\": 113}";

        givenThereIsAExistentShip(ship);

        assertRequestWithBody("POST", "/ships", ship, 409);
    }

    private void givenThereIsAExistentShip(String ship) throws Exception {
        assertRequestWithBody("POST", "/ships", ship, 201);

    }
}
