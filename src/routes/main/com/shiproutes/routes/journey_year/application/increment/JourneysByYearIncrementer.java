package com.shiproutes.routes.journey_year.application.increment;

import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.routes.journey_year.domain.JourneysByYearId;
import com.shiproutes.routes.journey_year.domain.JourneysByYearRepository;
import com.shiproutes.routes.shared.application.FindRoutePathQuery;
import com.shiproutes.routes.shared.application.RoutePathResponse;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.Year;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class JourneysByYearIncrementer {

    private final JourneysByYearRepository repository;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;

    public JourneysByYearIncrementer(JourneysByYearRepository repository, UuidGenerator uuidGenerator,
                                     QueryBus queryBus) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
    }

    public void increment(PortId originPort, PortId destinationPort, Teus teus, Year year) {

        JourneysByYear journeysByYear = repository.search(originPort, destinationPort, year)
            .orElseGet(() -> createJourneysByYear(originPort, destinationPort, year));

        journeysByYear.incrementJourneys();
        journeysByYear.incrementTeus(teus);

        repository.save(journeysByYear);
    }

    private JourneysByYear createJourneysByYear(PortId originPort, PortId destinationPort, Year year) {
        JourneysByYearId id = new JourneysByYearId(uuidGenerator.generate());
        RoutePath path = findRoutePath(originPort, destinationPort);
        return JourneysByYear.create(id, originPort, destinationPort, path, year);
    }

    private RoutePath findRoutePath(PortId originPort, PortId destinationPort) {
        RoutePathResponse routePath = queryBus.ask(new FindRoutePathQuery(originPort.value(), destinationPort.value()));
        return RoutePath.fromPrimitives(routePath.path());
    }

}
