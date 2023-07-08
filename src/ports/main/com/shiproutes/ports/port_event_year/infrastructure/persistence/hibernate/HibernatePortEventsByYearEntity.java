package com.shiproutes.ports.port_event_year.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.Coordinates;
import com.shiproutes.ports.shared.domain.Latitude;
import com.shiproutes.ports.shared.domain.Longitude;
import com.shiproutes.ports.port_event_year.domain.*;
import com.shiproutes.shared.domain.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "port_events_year")
@Table(name = "port_events_year")
public class HibernatePortEventsByYearEntity {

    @Id
    private String id;
    private String type;
    private String portId;
    private Double latitude;
    private Double longitude;
    private Integer year;
    private Long departures;
    private Long arrivals;

    public HibernatePortEventsByYearEntity() {
    }

    public HibernatePortEventsByYearEntity(PortEventsByYear portEventsByYear) {
        this.id = portEventsByYear.id().value();
        this.type = portEventsByYear.type().value();
        this.portId = portEventsByYear.portId().value();
        this.latitude = portEventsByYear.coordinates().latitude().value();
        this.longitude = portEventsByYear.coordinates().longitude().value();
        this.year = portEventsByYear.year().value();
        this.departures = portEventsByYear.departures().value();
        this.arrivals = portEventsByYear.arrivals().value();
    }

    public PortEventsByYear toEntity() {
        return new PortEventsByYear(
            new PortEventsByYearId(this.id),
            PortEventType.valueOf(type),
            new PortId(this.portId),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ),
            new Year(this.year),
            new TotalDepartures(this.departures),
            new TotalArrivals(this.arrivals)
        );
    }

}
