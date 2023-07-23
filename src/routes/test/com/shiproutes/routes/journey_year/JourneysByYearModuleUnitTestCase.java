package com.shiproutes.routes.journey_year;

import com.shiproutes.routes.journey_year.domain.JourneysByYear;
import com.shiproutes.routes.journey_year.domain.JourneysByYearRepository;
import com.shiproutes.routes.shared.application.FindRoutePathQuery;
import com.shiproutes.routes.shared.application.RoutePathResponse;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JourneysByYearModuleUnitTestCase extends UnitTestCase {

    protected JourneysByYearRepository repository;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(JourneysByYearRepository.class);
        when(repository.search(any(), any(), any())).thenReturn(Optional.empty());
    }

    protected void shouldHaveSaved(JourneysByYear journeysByYear) {
        verify(repository, atLeastOnce()).save(journeysByYear);
    }

    protected void shouldExists(JourneysByYear journeysByYear) {
        when(repository.search(
            journeysByYear.originPort(),
            journeysByYear.destinationPort(),
            journeysByYear.year())
        ).thenReturn(Optional.of(journeysByYear));
    }

    protected void shouldExistRoutePath(JourneysByYear journeysByYear) {
        shouldAsk(
            new FindRoutePathQuery(journeysByYear.originPort().value(), journeysByYear.destinationPort().value()),
            new RoutePathResponse(journeysByYear.path().toPrimitives())
        );
    }
}
