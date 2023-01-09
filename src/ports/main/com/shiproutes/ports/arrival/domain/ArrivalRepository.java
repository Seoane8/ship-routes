package com.shiproutes.ports.arrival.domain;

import java.util.Optional;

public interface ArrivalRepository {

    void save(Arrival arrival);

    Optional<Arrival> search(ArrivalId id);
}
