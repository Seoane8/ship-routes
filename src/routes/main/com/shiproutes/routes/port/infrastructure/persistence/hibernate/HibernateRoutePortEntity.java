package com.shiproutes.routes.port.infrastructure.persistence.hibernate;

import com.shiproutes.routes.port.domain.RoutePort;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "port")
@Table(name = "ports")
public final class HibernateRoutePortEntity {

    @Id
    private String id;
    private Double latitude;
    private Double longitude;

    public HibernateRoutePortEntity() {
    }

    public HibernateRoutePortEntity(RoutePort port) {
        this.id = port.id().value();
        this.latitude = port.coordinates().latitude().value();
        this.longitude = port.coordinates().longitude().value();
    }

    public RoutePort toEntity() {
        return new RoutePort(
            new PortId(id),
            new Coordinates(
                new Latitude(latitude),
                new Longitude(longitude)
            )
        );
    }

}
