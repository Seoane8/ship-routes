package com.shiproutes.routes.journey_month.infrastructure.persistence.hibernate;

import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthId;
import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.routes.shared.infrastructure.persistence.hibernate.RoutePathConverter;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.ports.TeusCounter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "journeysByMonth")
@Table(name = "journeys_month")
public class HibernateJourneysByMonthEntity {

    @Id
    private String id;
    private String originPort;
    private String destinationPort;
    @Convert(converter = RoutePathConverter.class)
    private List<List<Double>> path;
    private Integer month;
    private Integer year;
    private Long journeys;
    private Integer teus;

    public HibernateJourneysByMonthEntity() {
    }

    public HibernateJourneysByMonthEntity(JourneysByMonth journeysByMonth) {
        this.id = journeysByMonth.id().value();
        this.originPort = journeysByMonth.originPort().value();
        this.destinationPort = journeysByMonth.destinationPort().value();
        this.path = journeysByMonth.path().toPrimitives();
        this.month = journeysByMonth.month().value();
        this.year = journeysByMonth.year().value();
        this.journeys = journeysByMonth.journeys().value();
        this.teus = journeysByMonth.teus().value();
    }

    public JourneysByMonth toEntity() {
        return new JourneysByMonth(
            new JourneysByMonthId(this.id),
            new PortId(this.originPort),
            new PortId(this.destinationPort),
            RoutePath.fromPrimitives(this.path),
            new Month(this.month),
            new Year(this.year),
            new JourneysCounter(this.journeys),
            new TeusCounter(this.teus)
        );
    }
}
