package com.shiproutes.routes.route.infrastructure.persistence.hibernate;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteDistance;
import com.shiproutes.routes.route.domain.RouteId;
import com.shiproutes.shared.domain.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "route")
@Table(name = "routes")
public final class HibernateRouteEntity {

    @Id
    private String id;

    private String departurePort;

    private String arrivalPort;

    private Integer distance;

    public HibernateRouteEntity() {
    }

    public HibernateRouteEntity(Route route) {
        this.id = route.id().value();
        this.departurePort = route.departurePort().value();
        this.arrivalPort = route.arrivalPort().value();
        this.distance = route.distance().value();
    }

    public Route toEntity() {
        return new Route(
            new RouteId(this.id),
            new PortId(this.departurePort),
            new PortId(this.arrivalPort),
            new RouteDistance(this.distance)
        );
    }
}
