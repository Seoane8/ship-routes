package com.shiproutes.routes.journey.infrastructure.persistence.hibernate;

import com.shiproutes.routes.journey.domain.ArrivalDate;
import com.shiproutes.routes.journey.domain.DepartureDate;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyId;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.routes.shared.infrastructure.persistence.hibernate.RoutePathConverter;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.ports.PortId;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Entity(name = "journey")
@Table(name = "journeys")
public class HibernateJourneyEntity {

    @Id
    private String id;
    private String shipId;
    private Integer teus;
    private String originPort;
    private String destinationPort;
    private Instant departureDate;
    private Instant arrivalDate;
    @Convert(converter = RoutePathConverter.class)
    private List<List<Double>> path;

    public HibernateJourneyEntity() {
    }

    public HibernateJourneyEntity(Journey journey) {
        this.id = journey.id().value();
        this.shipId = journey.shipId().value();
        this.teus = journey.teus().value();
        this.originPort = journey.originPort().value();
        this.destinationPort = journey.destinationPort().value();
        this.departureDate = journey.departureDate().value();
        this.arrivalDate = journey.arrivalDate().value();
        this.path = journey.path().toPrimitives();
    }

    public Journey toEntity() {
        return new Journey(
            new JourneyId(this.id),
            new IMO(this.shipId),
            new Teus(this.teus),
            originPort == null ? PortId.empty() : new PortId(this.originPort),
            destinationPort == null ? PortId.empty() : new PortId(this.destinationPort),
            new DepartureDate(this.departureDate),
            new ArrivalDate(this.arrivalDate),
            RoutePath.fromPrimitives(this.path)
        );
    }
}
