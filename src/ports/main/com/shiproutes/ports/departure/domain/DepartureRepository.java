package com.shiproutes.ports.departure.domain;

import java.util.Optional;

public interface DepartureRepository {

    void save(Departure departure);

    Optional<Departure> search(DepartureId id);

}
