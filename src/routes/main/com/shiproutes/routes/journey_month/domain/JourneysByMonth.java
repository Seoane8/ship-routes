package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Objects;

public class JourneysByMonth {

    private final JourneysByMonthId id;
    private final PortId originPort;
    private final PortId destinationPort;
    private final RoutePath path;
    private final Month month;
    private final Year year;
    private JourneysCounter journeys;

    public JourneysByMonth(JourneysByMonthId id, PortId originPort, PortId destinationPort, RoutePath path,
                           Month month, Year year, JourneysCounter journeys) {
        this.id = id;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
        this.month = month;
        this.year = year;
        this.journeys = journeys;
    }

    public static JourneysByMonth create(JourneysByMonthId id, PortId originPort, PortId destinationPort,
                                         RoutePath path, Month month, Year year) {
        return new JourneysByMonth(id, originPort, destinationPort, path, month, year, JourneysCounter.initialize());
    }

    public JourneysByMonthId id() {
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

    public Month month() {
        return month;
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
        if (!(o instanceof JourneysByMonth)) return false;
        JourneysByMonth that = (JourneysByMonth) o;
        return Objects.equals(id, that.id) && Objects.equals(originPort, that.originPort)
            && Objects.equals(destinationPort, that.destinationPort) && Objects.equals(path, that.path)
            && Objects.equals(month, that.month) && Objects.equals(year, that.year)
            && Objects.equals(journeys, that.journeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originPort, destinationPort, path, month, year, journeys);
    }
}
