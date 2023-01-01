package com.shiproutes.apps.ships.backend.controller.health_check;

import com.shiproutes.apps.ApplicationTestCase;
import org.junit.jupiter.api.Test;

final class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {

        assertResponse("/health-check", 200, "{'application':'ships','status':'ok'}");
    }
}
