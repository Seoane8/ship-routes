package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.MotherCreator;
import com.shiproutes.shared.domain.ports.CoordinatesMother;

import java.util.stream.IntStream;

public class RoutePathMother {

    public static RoutePath random() {
        return IntStream.range(0, MotherCreator.random().random().nextInt(2, 200))
            .mapToObj(i -> CoordinatesMother.random())
            .collect(RoutePath.collector());
    }

    public static RoutePath update(RoutePath path) {
        RoutePath newPath = RoutePathMother.random();
        newPath.add(0, path.origin());
        newPath.add(path.destination());
        return newPath;
    }

    public static RoutePath mismatch(RoutePath path) {
        RoutePath newPath;
        do {
            newPath = RoutePathMother.random();
        } while (newPath.origin().equals(path.origin()) && newPath.destination().equals(path.destination()));
        return newPath;
    }
}
