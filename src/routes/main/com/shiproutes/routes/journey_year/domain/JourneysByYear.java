package com.shiproutes.routes.journey_year.domain;

import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Objects;

public class JourneysByYear {

    private final JourneysByYearId id;
    private final PortId originPort;
    private final PortId destinationPort;
    private final RoutePath path;
    private final Year year;
    private JourneysCounter journeys;

    public JourneysByYear(JourneysByYearId id, PortId originPort, PortId destinationPort, RoutePath path,
                          Year year, JourneysCounter journeys) {
        this.id = id;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
        this.year = year;
        this.journeys = journeys;
    }

    public static JourneysByYear create(JourneysByYearId id, PortId originPort, PortId destinationPort,
                                        RoutePath path, Year year) {
        return new JourneysByYear(id, originPort, destinationPort, path, year, JourneysCounter.initialize());
    }

    public JourneysByYearId id() {
        return id;
    }

    public PortId originPort() {
        return originPort;
    }

    public PortId destinationPort() {
        return destinationPort;
    }

    public RoutePath path() {
        return path;
    }


    public Year year() {
        return year;
    }

    public JourneysCounter journeys() {
        return journeys;
    }

    public void incrementJourneys() {
        this.journeys = journeys.increment();
    }

    public void decrementJourneys() {
        this.journeys = journeys.decrement();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneysByYear)) return false;
        JourneysByYear that = (JourneysByYear) o;
        return Objects.equals(id, that.id) && Objects.equals(originPort, that.originPort)
            && Objects.equals(destinationPort, that.destinationPort) && Objects.equals(path, that.path)
            && Objects.equals(year, that.year)
            && Objects.equals(journeys, that.journeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originPort, destinationPort, path, year, journeys);
    }
}
