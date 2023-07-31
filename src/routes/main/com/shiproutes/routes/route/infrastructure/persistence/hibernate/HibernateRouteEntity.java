package com.shiproutes.routes.route.infrastructure.persistence.hibernate;

import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteId;
import com.shiproutes.routes.shared.domain.JourneysCounter;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.routes.shared.infrastructure.persistence.hibernate.RoutePathConverter;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.ports.TeusCounter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "route")
@Table(name = "routes")
public final class HibernateRouteEntity {

    @Id
    private String id;
    private String originPort;
    private String destinationPort;
    @Convert(converter = RoutePathConverter.class)
    private List<List<Double>> path;
    private Long journeys;
    private Integer teus;

    public HibernateRouteEntity() {
    }

    public HibernateRouteEntity(Route route) {
        this.id = route.id().value();
        this.originPort = route.originPort().value();
        this.destinationPort = route.destinationPort().value();
        this.path = route.path().toPrimitives();
        this.journeys = route.journeys().value();
        this.teus = route.teus().value();
    }

    public Route toEntity() {
        return new Route(
            new RouteId(this.id),
            new PortId(this.originPort),
            new PortId(this.destinationPort),
            RoutePath.fromPrimitives(this.path),
            new JourneysCounter(this.journeys),
            new TeusCounter(this.teus)
        );
    }
}
