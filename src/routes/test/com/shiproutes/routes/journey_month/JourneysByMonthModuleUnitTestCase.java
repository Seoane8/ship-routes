package com.shiproutes.routes.journey_month;

import com.shiproutes.routes.journey_month.domain.JourneysByMonth;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthRepository;
import com.shiproutes.routes.shared.application.FindRoutePathQuery;
import com.shiproutes.routes.shared.application.RoutePathResponse;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JourneysByMonthModuleUnitTestCase extends UnitTestCase {

    protected JourneysByMonthRepository repository;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(JourneysByMonthRepository.class);
        when(repository.search(any(), any(), any(), any())).thenReturn(Optional.empty());
    }

    protected void shouldHaveSaved(JourneysByMonth journeysByMonth) {
        verify(repository, atLeastOnce()).save(journeysByMonth);
    }

    protected void shouldExists(JourneysByMonth journeysByMonth) {
        when(repository.search(
            journeysByMonth.originPort(),
            journeysByMonth.destinationPort(),
            journeysByMonth.month(),
            journeysByMonth.year())
        ).thenReturn(Optional.of(journeysByMonth));
    }

    protected void shouldExistRoutePath(JourneysByMonth journeysByMonth) {
        shouldAsk(
            new FindRoutePathQuery(journeysByMonth.originPort().value(), journeysByMonth.destinationPort().value()),
            new RoutePathResponse(journeysByMonth.path().toPrimitives())
        );
    }
}
