package com.shiproutes.ports.port_event_month.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port_event_month.domain.Month;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthId;
import com.shiproutes.ports.port_event_year.domain.Year;
import com.shiproutes.ports.shared.domain.*;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.coordinates.Coordinates;
import com.shiproutes.shared.domain.coordinates.Latitude;
import com.shiproutes.shared.domain.coordinates.Longitude;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "portEventsByMonth")
@Table(name = "port_events_month")
public class HibernatePortEventsByMonthEntity {

    @Id
    private String id;
    private String portId;
    private Double latitude;
    private Double longitude;
    private Integer year;
    private Integer month;
    private Long departures;
    private Long arrivals;

    public HibernatePortEventsByMonthEntity() {
    }

    public HibernatePortEventsByMonthEntity(PortEventsByMonth portEventsByMonth) {
        this.id = portEventsByMonth.id().value();
        this.portId = portEventsByMonth.portId().value();
        this.latitude = portEventsByMonth.coordinates().latitude().value();
        this.longitude = portEventsByMonth.coordinates().longitude().value();
        this.year = portEventsByMonth.year().value();
        this.month = portEventsByMonth.month().value();
        this.departures = portEventsByMonth.departures().value();
        this.arrivals = portEventsByMonth.arrivals().value();
    }

    public PortEventsByMonth toEntity() {
        return new PortEventsByMonth(
            new PortEventsByMonthId(this.id),
            new PortId(this.portId),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ),
            new Year(this.year),
            new Month(this.month),
            new TotalDepartures(this.departures),
            new TotalArrivals(this.arrivals)
        );
    }
}
