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

}
