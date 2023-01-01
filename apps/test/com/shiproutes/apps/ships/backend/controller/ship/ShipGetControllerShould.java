package com.shiproutes.apps.ships.backend.controller.ship;

import com.shiproutes.apps.ships.backend.controller.ShipApplicationTestCase;
import org.junit.jupiter.api.Test;

class ShipGetControllerShould extends ShipApplicationTestCase {

    @Test
    public void find_an_existent_ship() throws Exception {
        String imo = "7052363";
        String ship = String.format("{\"imo\": \"%s\", \"name\": \"ASTRON\", \"teus\": 113}", imo);

        givenThereIsAExistentShip(ship);

        assertResponse(String.format("/ships/%s", imo), 200, ship);
    }

    @Test
    public void no_find_a_non_existent_ship() throws Exception {
        String imo = "7052363";
        String errorBody = String.format("{\"error_code\": \"ship_not_exists\", \"message\": \"The ship <%s> doesn't " +
            "exist\"}", imo);

        assertResponse(String.format("/ships/%s", imo), 404, errorBody);
    }

    private void givenThereIsAExistentShip(String ship) throws Exception {
        assertRequestWithBody("POST", "/ships", ship, 201);

    }

}
