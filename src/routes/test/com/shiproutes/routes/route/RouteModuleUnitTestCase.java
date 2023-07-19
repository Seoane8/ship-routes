package com.shiproutes.routes.route;

import com.shiproutes.routes.port.application.find_coordinates.CoordinatesResponse;
import com.shiproutes.routes.port.application.find_coordinates.FindCoordinatesQuery;
import com.shiproutes.routes.route.domain.PathGenerator;
import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RoutePath;
import com.shiproutes.routes.route.domain.RouteRepository;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class RouteModuleUnitTestCase extends UnitTestCase {

    protected RouteRepository repository;
    protected PathGenerator pathGenerator;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(RouteRepository.class);
        pathGenerator = mock(PathGenerator.class);
    }

    protected void shouldHaveSaved(Route route) {
        verify(repository, atLeastOnce()).save(route);
    }

    protected void shouldNotHaveSaved(Route route) {
        verify(repository, never()).save(route);
    }

    protected void shouldExists(Route route) {
        when(repository.search(route.id())).thenReturn(Optional.of(route));
        when(repository.search(route.departurePort(), route.arrivalPort())).thenReturn(Optional.of(route));
    }

    protected void shouldNotExists(Route route) {
        when(repository.search(route.id())).thenReturn(Optional.empty());
        when(repository.search(route.departurePort(), route.arrivalPort())).thenReturn(Optional.empty());
    }

    protected void shouldExistRoutePorts(Route route) {
        shouldExistPortWithCoordinates(route.departurePort(), route.departureCoordinates());
        shouldExistPortWithCoordinates(route.arrivalPort(), route.arrivalCoordinates());
    }

    private void shouldExistPortWithCoordinates(PortId portId, Coordinates coordinates) {
        shouldAsk(
            new FindCoordinatesQuery(portId.value()),
            new CoordinatesResponse(
                coordinates.latitude().value(),
                coordinates.longitude().value()
            )
        );
    }

    protected void shouldGeneratePath(RoutePath path) {
        when(pathGenerator.generate(path.origin(), path.destination())).thenReturn(path);
    }
}
