package com.shiproutes.apps.ports.backend.controller.port;

import com.shiproutes.apps.ports.backend.controller.PortApplicationTestCase;
import org.junit.jupiter.api.Test;

class PortsGetControllerShould extends PortApplicationTestCase {

    @Test
    void return_empty_list_when_no_port_exist() throws Exception {
        assertResponse(
            "/ports",
            200,
            "[]"
        );
    }

    @Test
    public void return_existent_ports() throws Exception {
        String port = "{" +
            "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
            "\"name\": \"Dunkerque\", " +
            "\"locode\": \"FRDKK\", " +
            "\"latitude\": 51.0455, " +
            "\"longitude\": 2.1543" +
            "}";

        givenThenIsAExistentPort(port);

        assertResponse("/ports", 200, "[{" +
            "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
            "\"name\": \"Dunkerque\", " +
            "\"locode\": \"FRDKK\", " +
            "\"latitude\": 51.0455, " +
            "\"longitude\": 2.1543," +
            "\"departures\": 0," +
            "\"arrivals\": 0" +
            "}]");
    }
}
