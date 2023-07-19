package com.shiproutes.ports.port.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port.domain.Locode;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortName;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "port")
@Table(name = "ports")
public final class HibernatePortEntity {

    @Id
    private String id;
    private String name;
    private String locode;
    private Double latitude;
    private Double longitude;
    private Long departures;
    private Long arrivals;

    public HibernatePortEntity() {
    }

    public HibernatePortEntity(Port port) {
        this.id = port.id().value();
        this.name = port.name().value();
        this.locode = port.locode().value();
        this.latitude = port.coordinates().latitude().value();
        this.longitude = port.coordinates().longitude().value();
        this.departures = port.totalDepartures().value();
        this.arrivals = port.totalArrivals().value();
    }

    public Port toEntity() {
        return new Port(
            new PortId(this.id),
            new PortName(this.name),
            new Locode(this.locode),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ),
            new TotalDepartures(this.departures),
            new TotalArrivals(this.arrivals)
        );
    }
}
