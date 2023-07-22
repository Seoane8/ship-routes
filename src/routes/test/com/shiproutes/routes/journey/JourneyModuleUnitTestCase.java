package com.shiproutes.routes.journey;

import com.shiproutes.routes.journey.domain.ArrivalDate;
import com.shiproutes.routes.journey.domain.DepartureDate;
import com.shiproutes.routes.journey.domain.Journey;
import com.shiproutes.routes.journey.domain.JourneyRepository;
import com.shiproutes.routes.route.application.find_path.FindRoutePathQuery;
import com.shiproutes.routes.route.application.find_path.RoutePathResponse;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class JourneyModuleUnitTestCase extends UnitTestCase {

    protected JourneyRepository repository;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(JourneyRepository.class);
        when(repository.search(any())).thenReturn(Optional.empty());
        when(repository.searchJourneyArrival(any(), any())).thenReturn(Optional.empty());
        when(repository.searchJourneyDeparture(any(), any())).thenReturn(Optional.empty());
    }

    protected void shouldHaveSaved(Journey journey) {
        verify(repository, atLeastOnce()).save(journey);
    }

    protected void shouldHaveRemoved(Journey journey) {
        verify(repository, atLeastOnce()).remove(journey);
    }

    protected void shouldExists(Journey journey) {
        when(repository.search(journey.id())).thenReturn(Optional.of(journey));
        when(repository.searchJourneyArrival(eq(journey.shipId()), departureDate(journey)))
            .thenReturn(Optional.of(journey));
        when(repository.searchJourneyDeparture(eq(journey.shipId()), arrivalDate(journey)))
            .thenReturn(Optional.of(journey));
    }

    private DepartureDate departureDate(Journey journey) {
        return ArgumentMatchers.argThat(departureDate ->
            journey.arrivalDate().isAfter(departureDate) &&
                (journey.departureDate().isEmpty() || journey.departureDate().isBefore(departureDate)));
    }

    private ArrivalDate arrivalDate(Journey journey) {
        return ArgumentMatchers.argThat(arrivalDate ->
            journey.departureDate().isBefore(arrivalDate) &&
                (journey.arrivalDate().isEmpty() || journey.arrivalDate().isAfter(arrivalDate)));
    }

    protected void shouldExistRoutePath(Journey journey) {
        shouldAsk(
            new FindRoutePathQuery(journey.originPort().value(), journey.destinationPort().value()),
            new RoutePathResponse(journey.path().toPrimitives())
        );
    }

}
