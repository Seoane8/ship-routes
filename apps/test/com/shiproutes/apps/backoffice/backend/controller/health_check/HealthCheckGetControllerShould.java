package com.shiproutes.apps.backoffice.backend.controller.health_check;

import com.shiproutes.apps.backoffice.backend.controller.BackofficeApplicationTestCase;
import org.junit.jupiter.api.Test;

class HealthCheckGetControllerShould extends BackofficeApplicationTestCase {

    @Test
    void check_the_app_is_working_ok() throws Exception {

        assertResponse("/health-check", 200, "{'application':'backoffice','status':'ok'}");
    }

}
