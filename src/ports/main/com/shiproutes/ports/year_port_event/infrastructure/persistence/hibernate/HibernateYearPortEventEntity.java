package com.shiproutes.ports.year_port_event.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.Coordinates;
import com.shiproutes.ports.shared.domain.Latitude;
import com.shiproutes.ports.shared.domain.Longitude;
import com.shiproutes.ports.year_port_event.domain.*;
import com.shiproutes.shared.domain.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "year_port_event")
@Table(name = "year_port_events")
public class HibernateYearPortEventEntity {

    @Id
    private String id;
    private String type;
    private String portId;
    private Double latitude;
    private Double longitude;
    private Integer year;
    private Long departures;
    private Long arrivals;

    public HibernateYearPortEventEntity() {
    }

    public HibernateYearPortEventEntity(YearPortEvent yearPortEvent) {
        this.id = yearPortEvent.id().value();
        this.type = yearPortEvent.type().value();
        this.portId = yearPortEvent.portId().value();
        this.latitude = yearPortEvent.coordinates().latitude().value();
        this.longitude = yearPortEvent.coordinates().longitude().value();
        this.year = yearPortEvent.year().value();
        this.departures = yearPortEvent.departures().value();
        this.arrivals = yearPortEvent.arrivals().value();
    }

    public YearPortEvent toEntity() {
        return new YearPortEvent(
            new YearPortEventId(this.id),
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
