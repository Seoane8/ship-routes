package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.ports.TeusCounter;

import java.util.Objects;

public class JourneysByMonth {

    private final JourneysByMonthId id;
    private final PortId originPort;
    private final PortId destinationPort;
    private final RoutePath path;
    private final Month month;
    private final Year year;
    private JourneysCounter journeys;
    private TeusCounter teus;

    public JourneysByMonth(JourneysByMonthId id, PortId originPort, PortId destinationPort, RoutePath path,
                           Month month, Year year, JourneysCounter journeys, TeusCounter teus) {
        this.id = id;
        this.originPort = originPort;
        this.destinationPort = destinationPort;
        this.path = path;
        this.month = month;
        this.year = year;
        this.journeys = journeys;
        this.teus = teus;
    }

    public static JourneysByMonth create(JourneysByMonthId id, PortId originPort, PortId destinationPort,
                                         RoutePath path, Month month, Year year) {
        return new JourneysByMonth(id, originPort, destinationPort, path, month, year,
            JourneysCounter.initialize(), TeusCounter.initialize());
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

    public TeusCounter teus() {
        return teus;
    }

    public void incrementJourneys() {
        this.journeys = journeys.increment();
    }

    public void decrementJourneys() {
        this.journeys = journeys.decrement();
    }

    public void incrementTeus(Teus teus) {
        this.teus = this.teus.increment(teus);
    }

    public void decrementTeus(Teus teus) {
        this.teus = this.teus.decrement(teus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneysByMonth)) return false;
        JourneysByMonth that = (JourneysByMonth) o;
        return Objects.equals(id, that.id) && Objects.equals(originPort, that.originPort)
            && Objects.equals(destinationPort, that.destinationPort) && Objects.equals(path, that.path)
            && Objects.equals(month, that.month) && Objects.equals(year, that.year)
            && Objects.equals(journeys, that.journeys) && Objects.equals(teus, that.teus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originPort, destinationPort, path, month, year, journeys, teus);
    }
}
