package com.shiproutes.routes.route.domain;

import com.shiproutes.shared.domain.UuidMother;

public class RouteIdMother {

    public static RouteId random() {
        return new RouteId(UuidMother.random());
    }

}
