package com.shiproutes.apps.ports.backend.controller.port;

import com.shiproutes.apps.ports.backend.controller.PortApplicationTestCase;
import org.junit.jupiter.api.Test;

class PortPostControllerShould extends PortApplicationTestCase {

    @Test
    void create_a_valid_port() throws Exception {
        assertRequestWithBody(
            "POST", "/ports",
            "{" +
                "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
                "\"name\": \"Dunkerque\", " +
                "\"locode\": \"FRDKK\", " +
                "\"latitude\": 51.0455, " +
                "\"longitude\": 2.1543" +
            "}",
            201
        );
    }

    @Test
    void fail_when_port_is_invalid() throws Exception {
        assertRequestWithBody(
            "POST", "/ports",
            "{" +
                "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
                "\"name\": \"Dunkerque\", " +
                "\"locode\": \"GASOAWN\", " +
                "\"latitude\": 51.0455, " +
                "\"longitude\": 2.1543" +
                "}",
            400
        );
    }

    @Test
    void fail_when_test_already_exists() throws Exception {
        String port = "{" +
            "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
            "\"name\": \"Dunkerque\", " +
            "\"locode\": \"FRDKK\", " +
            "\"latitude\": 51.0455, " +
            "\"longitude\": 2.1543" +
        "}";

        givenThenIsAExistentPort(port);

        assertRequestWithBody("POST", "/ports", port, 409);
    }

    private void givenThenIsAExistentPort(String port) throws Exception {
        assertRequestWithBody("POST", "/ports", port, 201);
    }


}
