package com.shiproutes.routes.journey.application.create_from_departure;

import com.shiproutes.routes.journey.domain.DepartureDate;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyId;
import com.shiproutes.routes.journey.domain.JourneyRepository;
import com.shiproutes.routes.route.application.find_path.FindRoutePathQuery;
import com.shiproutes.routes.route.application.find_path.RoutePathResponse;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UuidGenerator;
import com.shiproutes.shared.domain.bus.query.QueryBus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

import java.util.Optional;

@Service
public class JourneyFromDepartureCreator {

    private final JourneyRepository repository;
    private final UuidGenerator uuidGenerator;
    private final QueryBus queryBus;

    public JourneyFromDepartureCreator(JourneyRepository repository, UuidGenerator uuidGenerator, QueryBus queryBus) {
        this.repository = repository;
        this.uuidGenerator = uuidGenerator;
        this.queryBus = queryBus;
    }

    public void create(IMO shipId, PortId originPort, DepartureDate departureDate) {
        JourneyId journeyId = new JourneyId(uuidGenerator.generate());

        Optional<Journey> optionalJourney = repository.searchJourneyArrival(shipId, departureDate);

        if (optionalJourney.isEmpty()){
            Journey newJourney = Journey.departure(journeyId, shipId, originPort, departureDate);
            repository.save(newJourney);
            return;
        }

        Journey journeyArrival = optionalJourney.get();

        RoutePath routePath = getPath(originPort, journeyArrival.destinationPort());
        Journey journey = Journey.create(journeyId, shipId, originPort, journeyArrival.destinationPort(),
            departureDate, journeyArrival.arrivalDate(), routePath);

        repository.remove(journeyArrival);
        repository.save(journey);
        if (journeyArrival.isComplete()) {
            this.create(shipId, journeyArrival.originPort(), journeyArrival.departureDate());
        }
    }

    public RoutePath getPath(PortId originPort, PortId destinationPort) {
        RoutePathResponse response =  queryBus.ask(new FindRoutePathQuery(originPort.value(), destinationPort.value()));
        return response.path().stream().map(coordinates -> new Coordinates(
            new Latitude(coordinates.get(0)),
            new Longitude(coordinates.get(1))
        )).collect(RoutePath.collector());
    }

}
