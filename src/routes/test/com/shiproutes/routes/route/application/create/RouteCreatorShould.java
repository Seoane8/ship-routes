package com.shiproutes.routes.route.application.create;

import com.shiproutes.routes.route.RouteModuleUnitTestCase;
import com.shiproutes.routes.route.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RouteCreatorShould extends RouteModuleUnitTestCase {

    RouteCreator creator;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        creator = new RouteCreator(repository, pathGenerator, queryBus, eventBus);
    }

    @Test
    void create_a_new_route() {
        Route route = RouteMother.random();
        shouldNotExists(route);
        shouldNotExists(RouteMother.reverse(route));
        shouldExistRoutePorts(route);
        shouldGeneratePath(route.path());

        creator.create(route.id(), route.originPort(), route.destinationPort());

        shouldHaveSaved(route);
    }

    @Test
    void create_a_new_route_using_existent_reversed_path() {
        Route route = RouteMother.random();
        shouldNotExists(route);
        shouldExists(RouteMother.reverse(route));

        creator.create(route.id(), route.originPort(), route.destinationPort());

        shouldHaveSaved(route);
    }

    @Test
    void publish_route_created_event() {
        Route route = RouteMother.random();
        RouteCreatedEvent event = RouteCreatedEventMother.fromRoute(route);
        shouldNotExists(route);
        shouldNotExists(RouteMother.reverse(route));
        shouldExistRoutePorts(route);
        shouldGeneratePath(route.path());

        creator.create(route.id(), route.originPort(), route.destinationPort());

        shouldHavePublished(event);
    }

    @Test
    void fail_when_route_already_exists() {
        assertThrows(RouteAlreadyExists.class, () -> {
            Route route = RouteMother.random();
            shouldExists(route);

            creator.create(route.id(), route.originPort(), route.destinationPort());
        });
    }

    @Test
    void fail_when_port_not_exist() {
        assertThrows(PortNotExist.class, () -> {
            Route route = RouteMother.random();
            shouldNotExists(route);
            shouldNotExists(RouteMother.reverse(route));
            shouldNotExistAnyRoutePort(route);
            shouldGeneratePath(route.path());

            creator.create(route.id(), route.originPort(), route.destinationPort());
        });
    }

}
