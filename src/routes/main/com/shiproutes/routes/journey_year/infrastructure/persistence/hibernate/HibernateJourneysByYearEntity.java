package com.shiproutes.routes.journey_year.infrastructure.persistence.hibernate;

import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.routes.journey_year.domain.JourneysByYearId;
import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.routes.shared.infrastructure.persistence.hibernate.RoutePathConverter;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "journeysByYear")
@Table(name = "journeys_year")
public class HibernateJourneysByYearEntity {

    @Id
    private String id;
    private String originPort;
    private String destinationPort;
    @Convert(converter = RoutePathConverter.class)
    private List<List<Double>> path;
    private Integer year;
    private Long journeys;

    public HibernateJourneysByYearEntity() {
    }

    public HibernateJourneysByYearEntity(JourneysByYear journeysByYear) {
        this.id = journeysByYear.id().value();
        this.originPort = journeysByYear.originPort().value();
        this.destinationPort = journeysByYear.destinationPort().value();
        this.path = journeysByYear.path().toPrimitives();
        this.year = journeysByYear.year().value();
        this.journeys = journeysByYear.journeys().value();
    }

    public JourneysByYear toEntity() {
        return new JourneysByYear(
            new JourneysByYearId(this.id),
            new PortId(this.originPort),
            new PortId(this.destinationPort),
            RoutePath.fromPrimitives(this.path),
            new Year(this.year),
            new JourneysCounter(this.journeys)
        );
    }
}
