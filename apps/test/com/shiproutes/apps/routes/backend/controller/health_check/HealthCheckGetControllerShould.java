package com.shiproutes.apps.routes.backend.controller.health_check;

import com.shiproutes.apps.ApplicationTestCase;
import org.junit.jupiter.api.Test;

class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {

        assertResponse("/health-check", 200, "{'application':'routes','status':'ok'}");
    }
}
