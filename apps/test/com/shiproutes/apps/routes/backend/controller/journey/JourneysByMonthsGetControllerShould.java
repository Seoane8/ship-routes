package com.shiproutes.apps.routes.backend.controller.journey;

import com.shiproutes.apps.routes.backend.controller.RoutesApplicationTestCase;
import com.shiproutes.routes.shared.domain.JourneyCreatedEvent;
import com.shiproutes.routes.shared.domain.JourneyRemovedEvent;
import com.shiproutes.shared.domain.ports.PortCreatedEvent;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class JourneysByMonthsGetControllerShould extends RoutesApplicationTestCase {

    @Test
    void return_empty_list_when_no_journeys_exist() throws Exception {
        assertResponse(
            "/journeys/months",
            200,
            "[]"
        );
    }

    @Test
    void return_existent_journeys_by_month() throws Exception {
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

        givenISendEventsToTheBus(
            new JourneyCreatedEvent(
                "e8c6e0ee-596f-4d8c-82d6-0c4dd1584b83",
                "043e6223-0b38-4483-8735-f9f4bc224f58",
                10000,
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                "f68caed9-6b5c-405b-ad4c-a50bf0ceae48",
                Instant.parse("2020-01-28T00:00:00Z"),
                Instant.parse("2020-01-30T00:00:00Z")
            ),
            new JourneyCreatedEvent(
                "3de3e633-64d5-4213-b527-575efc4dfde8",
                "ec704f4b-4a68-4740-a4ee-22844e0efad1",
                10000,
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                "f68caed9-6b5c-405b-ad4c-a50bf0ceae48",
                Instant.parse("2020-02-02T00:00:00Z"),
                Instant.parse("2020-02-10T00:00:00Z")
            ),
            new JourneyCreatedEvent(
                "8fdfe167-ea5e-4281-8287-005ec7103850",
                "2f0df52b-6c99-4e3c-9410-78051db93da9",
                10000,
                "f68caed9-6b5c-405b-ad4c-a50bf0ceae48",
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-07T00:00:00Z")
            ),
            new JourneyCreatedEvent(
                "02ad34ca-3a28-4d82-867c-d594ebcaf53e",
                "59395e1a-bcad-451d-a5a0-f97c041c2adc",
                10000,
                "f68caed9-6b5c-405b-ad4c-a50bf0ceae48",
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                Instant.parse("2021-01-05T00:00:00Z"),
                Instant.parse("2021-01-12T00:00:00Z")
            ),
            new JourneyRemovedEvent(
                "8fdfe167-ea5e-4281-8287-005ec7103850",
                "2f0df52b-6c99-4e3c-9410-78051db93da9",
                10000,
                "f68caed9-6b5c-405b-ad4c-a50bf0ceae48",
                "058a766b-fa60-49e9-88ed-2970380f5a2f",
                Instant.parse("2021-01-01T00:00:00Z"),
                Instant.parse("2021-01-07T00:00:00Z")
            )
        );

        assertResponse("/journeys/months", 200, "[" +
            "{" +
            "\"origin_port\": \"058a766b-fa60-49e9-88ed-2970380f5a2f\", " +
            "\"destination_port\": \"f68caed9-6b5c-405b-ad4c-a50bf0ceae48\"," +
            "\"path\": [[51.0455, 2.1543], [31.36636, 121.6147]]," +
            "\"journeys\": [" +
            "{" +
            "\"year\": 2020," +
            "\"month\": 1," +
            "\"total\": 1," +
            "\"teus\": 10000" +
            "},{" +
            "\"year\": 2020," +
            "\"month\": 2," +
            "\"total\": 1," +
            "\"teus\": 10000" +
            "}" +
            "]}," +
            "{" +
            "\"origin_port\": \"f68caed9-6b5c-405b-ad4c-a50bf0ceae48\", " +
            "\"destination_port\": \"058a766b-fa60-49e9-88ed-2970380f5a2f\"," +
            "\"path\": [[31.36636, 121.6147], [51.0455, 2.1543]]," +
            "\"journeys\": [" +
            "{" +
            "\"year\": 2021," +
            "\"month\": 1," +
            "\"total\": 1," +
            "\"teus\": 10000" +
            "}" +
            "]}" +
            "]");
    }

}
