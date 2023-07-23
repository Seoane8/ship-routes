package com.shiproutes.routes.journey_year.domain;

import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;
import java.util.Set;

public interface JourneysByYearRepository {

    void save(JourneysByYear journeysByMonth);

    Optional<JourneysByYear> search(PortId originPort, PortId destinationPort, Year year);

    Set<JourneysByYear> searchAll();
}
