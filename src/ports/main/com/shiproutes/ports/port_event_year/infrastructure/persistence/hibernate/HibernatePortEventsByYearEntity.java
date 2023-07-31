package com.shiproutes.ports.port_event_year.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port_event_year.domain.PortEventsByYear;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearId;
import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "portEventsByYear")
@Table(name = "port_events_year")
public class HibernatePortEventsByYearEntity {

    @Id
    private String id;
    private String portId;
    private String portName;
    private Double latitude;
    private Double longitude;
    private Integer year;
    private Long departures;
    private Long arrivals;
    private Integer teus;

    public HibernatePortEventsByYearEntity() {
    }

    public HibernatePortEventsByYearEntity(PortEventsByYear portEventsByYear) {
        this.id = portEventsByYear.id().value();
        this.portId = portEventsByYear.portId().value();
        this.portName = portEventsByYear.portName().value();
        this.latitude = portEventsByYear.coordinates().latitude().value();
        this.longitude = portEventsByYear.coordinates().longitude().value();
        this.year = portEventsByYear.year().value();
        this.departures = portEventsByYear.departures().value();
        this.arrivals = portEventsByYear.arrivals().value();
        this.teus = portEventsByYear.teusCounter().value();
    }

    public PortEventsByYear toEntity() {
        return new PortEventsByYear(
            new PortEventsByYearId(this.id),
            new PortId(this.portId),
            new PortName(this.portName),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ),
            new Year(this.year),
            new TotalDepartures(this.departures),
            new TotalArrivals(this.arrivals),
            new TeusCounter(this.teus)
        );
    }

}
