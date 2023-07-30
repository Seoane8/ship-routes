package com.shiproutes.apps.ingest.backend.controller.health_check;

import com.shiproutes.apps.ingest.backend.controller.IngestApplicationTestCase;
import org.junit.jupiter.api.Test;

class HealthCheckGetControllerShould extends IngestApplicationTestCase {

    @Test
    void check_the_app_is_working_ok() throws Exception {

        assertResponse("/health-check", 200, "{'application':'ingest','status':'ok'}");
    }

}
