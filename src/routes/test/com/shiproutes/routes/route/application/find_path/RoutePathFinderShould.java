package com.shiproutes.routes.route.application.find_path;

import com.shiproutes.routes.route.RouteModuleUnitTestCase;
import com.shiproutes.routes.route.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoutePathFinderShould extends RouteModuleUnitTestCase {

    RoutePathFinder finder;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        this.finder = new RoutePathFinder(
            this.repository,
            this.pathGenerator,
            this.uuidGenerator,
            this.queryBus,
            this.eventBus
        );
    }

    @Test
    void return_path_of_existent_route() {
        Route route = RouteMother.random();
        RoutePathResponse expectedPath = RoutePathResponseMother.of(route);
        shouldExists(route);

        RoutePathResponse response = finder.find(route.originPort(), route.destinationPort());

        assertEquals(expectedPath, response);
    }

    @Test
    void return_path_when_exist_reversed_route() {
        Route route = RouteMother.random();
        RoutePathResponse expectedPath = RoutePathResponseMother.reversed(route);
        shouldExists(route);
        shouldGenerateUuid(route.id().value());

        RoutePathResponse response = finder.find(route.destinationPort(), route.originPort());

        assertEquals(expectedPath, response);
    }

    @Test
    void generate_path_when_route_not_exist_in_any_direction() {
        Route route = RouteMother.random();
        RoutePathResponse expectedPath = RoutePathResponseMother.of(route);
        shouldNotExists(route);
        shouldExistRoutePorts(route);
        shouldGeneratePath(route.path());
        shouldGenerateUuid(route.id().value());

        RoutePathResponse response = finder.find(route.originPort(), route.destinationPort());

        assertEquals(expectedPath, response);
    }

    @Test
    void fail_when_port_not_exist() {
        assertThrows(PortNotExist.class, () -> {
            Route route = RouteMother.random();
            shouldNotExists(route);
            shouldNotExistAnyRoutePort(route);

            finder.find(route.originPort(), route.destinationPort());
        });
    }

    @Test
    void create_new_route_when_exist_reversed_route() {
        Route reversedRoute = RouteMother.random();
        shouldExists(reversedRoute);
        Route route = RouteMother.reverse(reversedRoute);
        shouldGenerateUuid(route.id().value());

        finder.find(route.originPort(), route.destinationPort());

        shouldHaveSaved(route);
    }

    @Test
    void publish_route_created_event_when_create_new_route_from_reversed() {
        Route reversedRoute = RouteMother.random();
        Route route = RouteMother.reverse(reversedRoute);
        RouteCreatedEvent event = RouteCreatedEventMother.fromRoute(route);
        shouldExists(reversedRoute);
        shouldGenerateUuid(route.id().value());

        finder.find(route.originPort(), route.destinationPort());

        shouldHavePublished(event);
    }

    @Test
    void create_new_route() {
        Route route = RouteMother.random();
        shouldNotExists(route);
        shouldExistRoutePorts(route);
        shouldGeneratePath(route.path());
        shouldGenerateUuid(route.id().value());

        finder.find(route.originPort(), route.destinationPort());

        shouldHaveSaved(route);
    }

    @Test
    void publish_route_created_event_when_create_new_route() {
        Route route = RouteMother.random();
        RouteCreatedEvent event = RouteCreatedEventMother.fromRoute(route);
        shouldNotExists(route);
        shouldExistRoutePorts(route);
        shouldGeneratePath(route.path());
        shouldGenerateUuid(route.id().value());

        finder.find(route.originPort(), route.destinationPort());

        shouldHavePublished(event);
    }
}
