package com.shiproutes.routes.journey.domain;

import java.util.Optional;

public interface JourneyRepository {

    void save(Journey journey);

    Optional<Journey> search(JourneyId id);

}
