package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.shared.domain.Month;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;

public interface JourneysByMonthRepository {

    void save(JourneysByMonth journeysByMonth);

    Optional<JourneysByMonth> search(PortId originPort, PortId destinationPort, Month month, Year year);

}
