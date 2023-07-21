package com.shiproutes.routes.journey.domain;

import com.shiproutes.shared.domain.UuidMother;

public class JourneyIdMother {

    public static JourneyId random() {
        return new JourneyId(UuidMother.random());
    }
}
