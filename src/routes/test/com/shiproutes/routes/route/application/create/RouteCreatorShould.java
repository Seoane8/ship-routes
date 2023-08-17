package com.shiproutes.routes.route.application.create;

import com.shiproutes.routes.route.RouteModuleUnitTestCase;
import com.shiproutes.routes.route.domain.*;
import com.shiproutes.routes.shared.domain.RoutePath;
import com.shiproutes.routes.shared.domain.RoutePathMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RouteCreatorShould extends RouteModuleUnitTestCase {

    RouteCreator creator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        creator = new RouteCreator(repository, queryBus, eventBus);
    }

    @Test
    void create_a_new_route() {
        Route route = RouteMother.randomNew();
        shouldExistRoutePorts(route);
        shouldNotExists(route);

        creator.create(route.id(), route.originPort(), route.destinationPort(), route.path());

        shouldHaveSaved(route);
    }

    @Test
    void update_existing_route_path() {
        Route existentRoute = RouteMother.random();
        Route updatedRoute = RouteMother.updatePath(existentRoute);
        shouldExistRoutePorts(existentRoute);
        shouldExists(existentRoute);

        creator.create(existentRoute.id(), updatedRoute.originPort(), updatedRoute.destinationPort(),
            updatedRoute.path());

        shouldHaveSaved(updatedRoute);
    }

    @Test
    void publish_route_created_event() {
        Route route = RouteMother.random();
        RouteCreatedEvent event = RouteCreatedEventMother.fromRoute(route);
        shouldExistRoutePorts(route);
        shouldNotExists(route);

        creator.create(route.id(), route.originPort(), route.destinationPort(), route.path());

        shouldHavePublished(event);
    }

    @Test
    void publish_route_updated_event() {
        Route existentRoute = RouteMother.random();
        Route updatedRoute = RouteMother.updatePath(existentRoute);
        RouteUpdatedEvent event = new RouteUpdatedEvent(
            updatedRoute.id().value(),
            updatedRoute.originPort().value(),
            updatedRoute.destinationPort().value(),
            updatedRoute.path().toPrimitives()
        );
        shouldExistRoutePorts(existentRoute);
        shouldExists(existentRoute);

        creator.create(existentRoute.id(), updatedRoute.originPort(), updatedRoute.destinationPort(),
            updatedRoute.path());

        shouldHavePublished(event);
    }

    @Test
    void fail_when_port_not_exist() {
        assertThrows(PortNotExist.class, () -> {
            Route route = RouteMother.random();
            shouldNotExistAnyRoutePort(route);

            creator.create(route.id(), route.originPort(), route.destinationPort(), route.path());
        });
    }

    @Test
    void fail_when_route_path_mismatch() {
        assertThrows(RoutePathMismatch.class, () -> {
            Route route = RouteMother.random();
            RoutePath path = RoutePathMother.mismatch(route.path());
            shouldExistRoutePorts(route);
            shouldNotExists(route);

            creator.create(route.id(), route.originPort(), route.destinationPort(), path);
        });
    }

    @Test
    void fail_when_route_mismatch_from_existent_route() {
        assertThrows(ExistentRouteMismatch.class, () -> {
            Route existentRoute = RouteMother.random();
            Route route = RouteMother.random();
            shouldExistRoutePorts(route);
            shouldExists(existentRoute);

            creator.create(existentRoute.id(), route.originPort(), route.destinationPort(), route.path());
        });
    }

    @Test
    void fail_when_route_already_exists() {
        assertThrows(RouteAlreadyExist.class, () -> {
            Route existentRoute = RouteMother.random();
            Route route = RouteMother.withSamePorts(existentRoute);
            shouldExistRoutePorts(route);
            shouldExists(existentRoute);

            creator.create(route.id(), route.originPort(), route.destinationPort(), route.path());
        });
    }
}
