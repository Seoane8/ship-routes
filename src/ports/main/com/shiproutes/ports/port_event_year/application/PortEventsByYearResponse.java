package com.shiproutes.ports.port_event_year.application;

import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.HashSet;
import java.util.Set;

public class PortEventsByYearResponse implements Response {

    private final String portId;
    private final Double longitude;
    private final Double latitude;
    private final Set<EventsResponse> events;

    public PortEventsByYearResponse(String portId, Double longitude, Double latitude, Set<EventsResponse> events) {
        this.portId = portId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.events = events;
    }

    public static PortEventsByYearResponse from(PortEventsByYear entity) {
        PortEventsByYearResponse response = new PortEventsByYearResponse(entity.portId().value(),
            entity.coordinates().longitude().value(), entity.coordinates().latitude().value(), new HashSet<>());
        response.addEvents(entity);
        return response;
    }

    public String portId() {
        return portId;
    }

    public Double longitude() {
        return longitude;
    }

    public Double latitude() {
        return latitude;
    }

    public Set<EventsResponse> events() {
        return events;
    }

    public void addEvents(PortEventsByYear events) {
        this.events.add(EventsResponse.fromEntity(events));
    }

    public static class EventsResponse {
        private final Integer year;
        private final Long departures;
        private final Long arrivals;

        public EventsResponse(Integer year, Long departures, Long arrivals) {
            this.year = year;
            this.departures = departures;
            this.arrivals = arrivals;
        }

        public static EventsResponse fromEntity(PortEventsByYear entity) {
            return new EventsResponse(entity.year().value(), entity.departures().value(), entity.arrivals().value());
        }

        public Integer year() {
            return year;
        }

        public Long departures() {
            return departures;
        }

        public Long arrivals() {
            return arrivals;
        }
    }
}
