package com.shiproutes.routes.journey_month.application.decrement;

import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthId;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthRepository;
import com.shiproutes.routes.shared.application.FindRoutePathQuery;
import com.shiproutes.routes.shared.application.RoutePathResponse;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.*;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class JourneysByMonthDecrementer {

    private final JourneysByMonthRepository repository;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;

    public JourneysByMonthDecrementer(JourneysByMonthRepository repository, UuidGenerator uuidGenerator, QueryBus queryBus) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
    }

    public void decrement(PortId originPort, PortId destinationPort, Teus teus, Month month, Year year) {

        JourneysByMonth journeysByMonth = repository.search(originPort, destinationPort, month, year)
            .orElseGet(() -> createJourneysByMonth(originPort, destinationPort, month, year));

        journeysByMonth.decrementJourneys();
        journeysByMonth.decrementTeus(teus);

        repository.save(journeysByMonth);
    }

    private JourneysByMonth createJourneysByMonth(PortId originPort, PortId destinationPort, Month month, Year year) {
        JourneysByMonthId id = new JourneysByMonthId(uuidGenerator.generate());
        RoutePath path = findRoutePath(originPort, destinationPort);
        return JourneysByMonth.create(id, originPort, destinationPort, path, month, year);
    }

    private RoutePath findRoutePath(PortId originPort, PortId destinationPort) {
        RoutePathResponse routePath = queryBus.ask(new FindRoutePathQuery(originPort.value(), destinationPort.value()));
        return RoutePath.fromPrimitives(routePath.path());
    }
}
