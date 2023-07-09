package com.shiproutes.ports.port_event_month.application;

import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.HashSet;
import java.util.Set;

public class PortEventsByMonthResponse implements Response {

    private final String portId;
    private final Double longitude;
    private final Double latitude;
    private final Set<EventsResponse> events;

    public PortEventsByMonthResponse(String portId, Double longitude, Double latitude, Set<EventsResponse> events) {
        this.portId = portId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.events = events;
    }

    public static PortEventsByMonthResponse from(PortEventsByMonth entity) {
        PortEventsByMonthResponse response = new PortEventsByMonthResponse(entity.portId().value(),
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

    public void addEvents(PortEventsByMonth events) {
        this.events.add(EventsResponse.fromEntity(events));
    }

    public static class EventsResponse {
        private final Integer year;
        private final Integer month;
        private final Long departures;
        private final Long arrivals;

        public EventsResponse(Integer year, Integer month, Long departures, Long arrivals) {
            this.year = year;
            this.month = month;
            this.departures = departures;
            this.arrivals = arrivals;
        }

        public static EventsResponse fromEntity(PortEventsByMonth entity) {
            return new EventsResponse(entity.year().value(), entity.month().value(),
                entity.departures().value(), entity.arrivals().value());
        }

        public Integer year() {
            return year;
        }

        public Integer month() {
            return month;
        }

        public Long departures() {
            return departures;
        }

        public Long arrivals() {
            return arrivals;
        }
    }
}
