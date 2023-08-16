package com.shiproutes.routes.route.infrastructure;

import com.shiproutes.routes.route.domain.PathGenerator;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import eu.europa.ec.eurostat.jgiscotools.feature.Feature;
import eu.europa.ec.eurostat.searoute.SeaRouting;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class EurostatPathGenerator implements PathGenerator {

    private final SeaRouting seaRouting;

    public EurostatPathGenerator() {
        this.seaRouting = new SeaRouting(5);
    }

    @Override
    public RoutePath generate(Coordinates origin, Coordinates destination) {
        Feature feature = seaRouting.getRoute(
            origin.longitude().value(), origin.latitude().value(),
            destination.longitude().value(), destination.latitude().value()
        );

        Geometry geometry = feature.getGeometry();
        RoutePath path = Arrays.stream(geometry.getCoordinates())
            .map(coordinate -> new Coordinates(
                new Latitude(coordinate.getY()),
                new Longitude(coordinate.getX())
            )).collect(RoutePath.collector());

        if (path.origin().equals(origin) && path.destination().equals(destination)) {
            return path;
        }

        if (path.origin().equals(destination) && path.destination().equals(origin)) {
            return path.reverse();
        }

        List<Coordinates> fixedPath = new ArrayList<>();
        List<Coordinates> iterationPath = new ArrayList<>();
        for (Coordinates point : path) {
            if (!point.equals(origin) && !point.equals(destination)) {
                iterationPath.add(point);
                continue;
            }
            if (fixedPath.isEmpty()) {
                iterationPath.add(point);
                Collections.reverse(iterationPath);
                fixedPath.addAll(iterationPath);
                iterationPath.clear();
            } else {
                fixedPath.addAll(iterationPath);
                iterationPath.clear();
                iterationPath.add(point);
            }
        }
        Collections.reverse(iterationPath);
        fixedPath.addAll(iterationPath);

        path = new RoutePath(fixedPath);

        return path.origin().equals(origin) ? path : path.reverse();
    }
}
