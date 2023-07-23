package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.shared.domain.UuidMother;

public class JourneysByMonthIdMother {

    public static JourneysByMonthId random() {
        return new JourneysByMonthId(UuidMother.random());
    }
}
