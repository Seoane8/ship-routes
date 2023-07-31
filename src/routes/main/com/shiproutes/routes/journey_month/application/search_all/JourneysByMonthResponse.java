package com.shiproutes.routes.journey_month.application.search_all;

import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JourneysByMonthResponse implements Response {

    private final String id;
    private final String originPort;
    private final String destinationPort;
    private final List<List<Double>> path;
    private final Set<JourneysResponse> journeys;

    public JourneysByMonthResponse(String id, String originPort, String destinationPort, List<List<Double>> path,
                                   Set<JourneysResponse> journeys) {
        this.id = id;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
        this.journeys = journeys;
    }

    public static JourneysByMonthResponse from(JourneysByMonth entity) {
        return new JourneysByMonthResponse(entity.id().value(), entity.originPort().value(),
            entity.destinationPort().value(), entity.path().toPrimitives(), new HashSet<>());
    }

    public String id() {
        return id;
    }

    public String originPort() {
        return originPort;
    }

    public String destinationPort() {
        return destinationPort;
    }

    public List<List<Double>> path() {
        return path;
    }

    public Set<JourneysResponse> journeys() {
        return journeys;
    }

    public void addJourneys(JourneysByMonth journeys) {
        this.journeys.add(JourneysResponse.from(journeys));
    }

    public static class JourneysResponse {
        private final Integer year;
        private final Integer month;
        private final Long journeys;
        private final Integer teus;

        public JourneysResponse(Integer year, Integer month, Long journeys, Integer teus) {
            this.year = year;
            this.month = month;
            this.journeys = journeys;
            this.teus = teus;
        }

        public static JourneysResponse from(JourneysByMonth entity) {
            return new JourneysResponse(entity.year().value(), entity.month().value(),
                entity.journeys().value(), entity.teus().value());
        }

        public Integer year() {
            return year;
        }

        public Integer month() {
            return month;
        }

        public Long journeys() {
            return journeys;
        }

        public Integer teus() {
            return teus;
        }
    }
}
