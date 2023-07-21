package com.shiproutes.apps.routes.backend.controller.route;

import com.shiproutes.apps.routes.backend.controller.RoutesApplicationTestCase;
import com.shiproutes.shared.domain.ports.PortCreatedEvent;
import org.junit.jupiter.api.Test;

class RoutePostControllerShould extends RoutesApplicationTestCase {

    @Test
    void create_a_valid_port() throws Exception {
        givenISendEventsToTheBus(
            new PortCreatedEvent(
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                "Dunkerque",
                "FRDKK",
                51.0455,
                2.1543
            ),
            new PortCreatedEvent(
                "f68caed9-6b5c-405b-ad4c-a50bf0ceae48",
                "Shanghai",
                "CNSHG",
                31.36636,
                121.6147
            )
        );
        assertRequestWithBody(
            "POST", "/routes",
            "{" +
                "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
                "\"origin\": \"058a766b-fa60-49e9-88ed-2970380f5a2f\", " +
                "\"destination\": \"f68caed9-6b5c-405b-ad4c-a50bf0ceae48\", " +
                "\"path\": [[51.0455, 2.1543], [31.36636, 121.6147]]" +
                "}",
            201
        );
    }

    @Test
    void update_existent_route() throws Exception {
        givenISendEventsToTheBus(
            new PortCreatedEvent(
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                "Dunkerque",
                "FRDKK",
                51.0455,
                2.1543
            ),
            new PortCreatedEvent(
                "f68caed9-6b5c-405b-ad4c-a50bf0ceae48",
                "Shanghai",
                "CNSHG",
                31.36636,
                121.6147
            )
        );

        String route = "{" +
            "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
            "\"origin\": \"058a766b-fa60-49e9-88ed-2970380f5a2f\", " +
            "\"destination\": \"f68caed9-6b5c-405b-ad4c-a50bf0ceae48\", " +
            "\"path\": [[51.0455, 2.1543], [31.36636, 121.6147]]" +
            "}";

        givenThenIsAExistentRoute(route);

        assertRequestWithBody("POST", "/routes", route, 201);
    }

    @Test
    void fail_when_port_not_exist() throws Exception {
        givenISendEventsToTheBus(
            new PortCreatedEvent(
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                "Dunkerque",
                "FRDKK",
                51.0455,
                2.1543
            )
        );
        assertRequestWithBody(
            "POST", "/routes",
            "{" +
                "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
                "\"origin\": \"058a766b-fa60-49e9-88ed-2970380f5a2f\", " +
                "\"destination\": \"f68caed9-6b5c-405b-ad4c-a50bf0ceae48\", " +
                "\"path\": [[51.0455, 2.1543], [31.36636, 121.6147]]" +
                "}",
            400
        );
    }

    @Test
    void fail_when_route_path_mismatch_port_coordinates() throws Exception {
        givenISendEventsToTheBus(
            new PortCreatedEvent(
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                "Dunkerque",
                "FRDKK",
                51.0455,
                2.1543
            )
        );
        assertRequestWithBody(
            "POST", "/routes",
            "{" +
                "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
                "\"origin\": \"058a766b-fa60-49e9-88ed-2970380f5a2f\", " +
                "\"destination\": \"f68caed9-6b5c-405b-ad4c-a50bf0ceae48\", " +
                "\"path\": [[51.0455, 2.1543], [43.36636, 103.6147]]" +
                "}",
            400
        );
    }

}
