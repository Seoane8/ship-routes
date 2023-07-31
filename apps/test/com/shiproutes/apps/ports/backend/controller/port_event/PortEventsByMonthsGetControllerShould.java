package com.shiproutes.apps.ports.backend.controller.port_event;

import com.shiproutes.apps.ports.backend.controller.PortApplicationTestCase;
import com.shiproutes.shared.domain.ports.PortEventCreated;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class PortEventsByMonthsGetControllerShould extends PortApplicationTestCase {

    @Test
    void return_empty_list_when_no_port_event_exist() throws Exception {
        assertResponse(
            "/event/months",
            200,
            "[]"
        );
    }

    @Test
    void return_existent_port_events_by_year() throws Exception {
        String port = "{" +
            "\"id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
            "\"name\": \"Dunkerque\", " +
            "\"locode\": \"FRDKK\", " +
            "\"latitude\": 51.0455, " +
            "\"longitude\": 2.1543" +
            "}";

        givenThenIsAExistentPort(port);

        givenISendEventsToTheBus(
            new PortEventCreated(
                "e8c6e0ee-596f-4d8c-82d6-0c4dd1584b83",
                "043e6223-0b38-4483-8735-f9f4bc224f58",
                "Dunkerque",
                "ARRIVAL",
                "b02b328b-da56-4120-9474-d766005d07ca",
                Instant.parse("2020-01-30T00:00:00Z"),
                10000
            ),
            new PortEventCreated(
                "3de3e633-64d5-4213-b527-575efc4dfde8",
                "043e6223-0b38-4483-8735-f9f4bc224f58",
                "Dunkerque",
                "DEPARTURE",
                "b02b328b-da56-4120-9474-d766005d07ca",
                Instant.parse("2020-02-02T00:00:00Z"),
                10000
            ),
            new PortEventCreated(
                "8fdfe167-ea5e-4281-8287-005ec7103850",
                "043e6223-0b38-4483-8735-f9f4bc224f58",
                "Dunkerque",
                "ARRIVAL",
                "b02b328b-da56-4120-9474-d766005d07ca",
                Instant.parse("2021-01-01T00:00:00Z"),
                10000
            ),
            new PortEventCreated(
                "02ad34ca-3a28-4d82-867c-d594ebcaf53e",
                "043e6223-0b38-4483-8735-f9f4bc224f58",
                "Dunkerque",
                "DEPARTURE",
                "b02b328b-da56-4120-9474-d766005d07ca",
                Instant.parse("2021-01-05T00:00:00Z"),
                10000
            )
        );

        assertResponse("/event/months", 200, "[{" +
            "\"port_id\": \"043e6223-0b38-4483-8735-f9f4bc224f58\", " +
            "\"port_name\": \"Dunkerque\"," +
            "\"longitude\": 2.1543," +
            "\"latitude\": 51.0455," +
            "\"events\": [" +
            "{" +
            "\"year\": 2020," +
            "\"month\": 1," +
            "\"departures\": 0," +
            "\"arrivals\": 1," +
            "\"teus\": 10000" +
            "},{" +
            "\"year\": 2020," +
            "\"month\": 2," +
            "\"departures\": 1," +
            "\"arrivals\": 0," +
            "\"teus\": 10000" +
            "},{" +
            "\"year\": 2021," +
            "\"month\": 1," +
            "\"departures\": 1," +
            "\"arrivals\": 1," +
            "\"teus\": 20000" +
            "}" +
            "]}]");
    }

}
