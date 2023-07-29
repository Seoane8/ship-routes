package com.shiproutes.routes.journey_year.application.search_all;

import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JourneysByYearResponse implements Response {

    private final String id;
    private final String originPort;
    private final String destinationPort;
    private final List<List<Double>> path;
    private final Set<JourneysResponse> journeys;

    public JourneysByYearResponse(String id, String originPort, String destinationPort, List<List<Double>> path,
                                  Set<JourneysResponse> journeys) {
        this.id = id;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
        this.journeys = journeys;
    }

    public static JourneysByYearResponse from(JourneysByYear entity) {
        return new JourneysByYearResponse(entity.id().value(), entity.originPort().value(),
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

    public void addJourneys(JourneysByYear journeys) {
        this.journeys.add(JourneysResponse.from(journeys));
    }

    public static class JourneysResponse {
        private final Integer year;
        private final Long journeys;

        public JourneysResponse(Integer year, Long journeys) {
            this.year = year;
            this.journeys = journeys;
        }

        public static JourneysResponse from(JourneysByYear entity) {
            return new JourneysResponse(entity.year().value(), entity.journeys().value());
        }

        public Integer year() {
            return year;
        }

        public Long journeys() {
            return journeys;
        }

    }
}