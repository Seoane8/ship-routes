package com.shiproutes.shared.domain;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public final class InstantMother {

    public static Instant random() {
        return MotherCreator.random().date().past(2000, TimeUnit.DAYS).toInstant();
    }
}
