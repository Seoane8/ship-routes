package com.shiproutes.routes.journey.application.create;

import com.shiproutes.routes.journey.domain.DepartureDate;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyId;
import com.shiproutes.routes.journey.domain.JourneyRepository;
import com.shiproutes.routes.shared.application.FindRoutePathQuery;
import com.shiproutes.routes.shared.application.RoutePathResponse;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.bus.event.EventBus;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;

@Service
public class JourneyFromDepartureCreator {

    private final JourneyRepository repository;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;
    private final EventBus eventBus;

    public JourneyFromDepartureCreator(JourneyRepository repository, UuidGenerator uuidGenerator,
                                       QueryBus queryBus, EventBus eventBus) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
        this.eventBus = eventBus;
    }

    public void create(IMO shipId, Teus teus, PortId originPort, DepartureDate departureDate) {
        JourneyId journeyId = new JourneyId(uuidGenerator.generate());

        Optional<Journey> optionalJourney = repository.searchJourneyArrival(shipId, departureDate);

        if (optionalJourney.isEmpty()) {
            Journey newJourney = Journey.departure(journeyId, shipId, teus, originPort, departureDate);
            repository.save(newJourney);
            return;
        }

        Journey journeyArrival = optionalJourney.get();

        RoutePath routePath = getPath(originPort, journeyArrival.destinationPort());
        Journey journey = Journey.create(journeyId, shipId, teus, originPort, journeyArrival.destinationPort(),
            departureDate, journeyArrival.arrivalDate(), routePath);

        repository.remove(journeyArrival);
        repository.save(journey);
        if (journeyArrival.isComplete()) {
            journeyArrival.recordRemovedEvent();
            journeyArrival.recordUnlinkedDepartureEvent();
        }
        eventBus.publish(journey.pullDomainEvents());
        eventBus.publish(journeyArrival.pullDomainEvents());
    }

    public RoutePath getPath(PortId originPort, PortId destinationPort) {
        RoutePathResponse response = queryBus.ask(new FindRoutePathQuery(originPort.value(), destinationPort.value()));
        return RoutePath.fromPrimitives(response.path());
    }

}
