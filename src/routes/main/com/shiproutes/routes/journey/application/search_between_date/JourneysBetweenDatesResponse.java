package com.shiproutes.routes.journey.application.search_between_date;

import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.List;

public class JourneysBetweenDatesResponse implements Response {

    private final String originPort;
    private final String destinationPort;
    private final List<List<Double>> path;
    private Long journeys;

    public JourneysBetweenDatesResponse(String originPort, String destinationPort, List<List<Double>> path,
                                        Long journeys) {
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
        this.journeys = journeys;
    }

    public static JourneysBetweenDatesResponse from(Journey journey) {
        return new JourneysBetweenDatesResponse(journey.originPort().value(), journey.destinationPort().value(),
            journey.path().toPrimitives(), 0L);
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

    public Long journeys() {
        return journeys;
    }

    public void incrementJourney() {
        this.journeys++;
    }
}
