package com.shiproutes.ports.port.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port.domain.*;
import com.shiproutes.shared.domain.PortId;

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

    public HibernatePortEntity() {
    }

    public HibernatePortEntity(Port port) {
        this.id = port.id().value();
        this.name = port.name().value();
        this.locode = port.locode().value();
        this.latitude = port.coordinates().latitude().value();
        this.longitude = port.coordinates().longitude().value();
    }

    public Port toEntity() {
        return new Port(
            new PortId(this.id),
            new PortName(this.name),
            new Locode(this.locode),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ));
    }
}
