package com.shiproutes.ports.port_event_month.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port_event_month.domain.PortEventsByMonth;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthId;
import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.ports.shared.domain.TeusCounter;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "portEventsByMonth")
@Table(name = "port_events_month")
public class HibernatePortEventsByMonthEntity {

    @Id
    private String id;
    private String portId;
    private String portName;
    private Double latitude;
    private Double longitude;
    private Integer year;
    private Integer month;
    private Long departures;
    private Long arrivals;
    private Integer teus;

    public HibernatePortEventsByMonthEntity() {
    }

    public HibernatePortEventsByMonthEntity(PortEventsByMonth portEventsByMonth) {
        this.id = portEventsByMonth.id().value();
        this.portId = portEventsByMonth.portId().value();
        this.portName = portEventsByMonth.portName().value();
        this.latitude = portEventsByMonth.coordinates().latitude().value();
        this.longitude = portEventsByMonth.coordinates().longitude().value();
        this.year = portEventsByMonth.year().value();
        this.month = portEventsByMonth.month().value();
        this.departures = portEventsByMonth.departures().value();
        this.arrivals = portEventsByMonth.arrivals().value();
        this.teus = portEventsByMonth.teusCounter().value();
    }

    public PortEventsByMonth toEntity() {
        return new PortEventsByMonth(
            new PortEventsByMonthId(this.id),
            new PortId(this.portId),
            new PortName(this.portName),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ),
            new Year(this.year),
            new Month(this.month),
            new TotalDepartures(this.departures),
            new TotalArrivals(this.arrivals),
            new TeusCounter(this.teus)
        );
    }
}
