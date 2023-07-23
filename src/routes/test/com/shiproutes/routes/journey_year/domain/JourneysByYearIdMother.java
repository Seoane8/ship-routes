package com.shiproutes.routes.journey_year.domain;

import com.shiproutes.shared.domain.UuidMother;

public class JourneysByYearIdMother {
    public static JourneysByYearId random() {
        return new JourneysByYearId(UuidMother.random());
    }
}
