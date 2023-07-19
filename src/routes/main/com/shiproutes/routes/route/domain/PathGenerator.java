package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.coordinates.Coordinates;

public interface PathGenerator {

    RoutePath generate(Coordinates origin, Coordinates destination);

}
