package com.shiproutes.routes.shared.domain;

import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RoutePath extends ArrayList<Coordinates> {

    public RoutePath(Collection<? extends Coordinates> c) {
        super(c);
    }

    public static RoutePath empty() {
        return new RoutePath(Collections.emptyList());
    }

    public static RoutePath fromPrimitives(List<List<Double>> primitives) {
        return primitives.stream().map(coordinates -> new Coordinates(
            new Latitude(coordinates.get(0)),
            new Longitude(coordinates.get(1))
        )).collect(RoutePath.collector());
    }

    public static Collector<Coordinates, Object, RoutePath> collector() {
        return Collectors.collectingAndThen(Collectors.toList(), RoutePath::new);
    }

    public Coordinates origin() {
        return this.get(0);
    }

    public Coordinates destination() {
        return this.get(this.size() - 1);
    }

    public RoutePath reverse() {
        RoutePath reversedPath = new RoutePath(this);
        Collections.reverse(reversedPath);
        return reversedPath;
    }

    public List<List<Double>> toPrimitives() {
        return this.stream()
            .map(coordinates -> List.of(coordinates.latitude().value(), coordinates.longitude().value()))
            .collect(Collectors.toList());
    }
}
