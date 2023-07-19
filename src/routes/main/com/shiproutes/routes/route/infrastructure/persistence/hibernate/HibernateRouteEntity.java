package com.shiproutes.routes.route.infrastructure.persistence.hibernate;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteId;
import com.shiproutes.routes.route.domain.RoutePath;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "route")
@Table(name = "routes")
public final class HibernateRouteEntity {

    @Id
    private String id;
    private String originPort;
    private String destinationPort;
    @Convert(converter = RoutePathConverter.class)
    private List<Double[]> path;

    public HibernateRouteEntity() {
    }

    public HibernateRouteEntity(Route route) {
        this.id = route.id().value();
        this.originPort = route.originPort().value();
        this.destinationPort = route.destinationPort().value();
        this.path = route.path().stream().map(coordinates -> new Double[]{
            coordinates.latitude().value(),
            coordinates.longitude().value()
        }).collect(Collectors.toList());

    }

    public Route toEntity() {
        return new Route(
            new RouteId(this.id),
            new PortId(this.originPort),
            new PortId(this.destinationPort),
            this.path.stream().map(coordinates -> new Coordinates(
                new Latitude(coordinates[0]),
                new Longitude(coordinates[1])
            )).collect(RoutePath.collector())
        );
    }
}
