package com.shiproutes.routes.route.application.create;

import com.shiproutes.routes.route.RouteModuleUnitTestCase;
import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        creator.create(route.id(), route.departurePort(), route.arrivalPort());

        shouldHaveSaved(route);
    }

    @Test
    void create_a_new_route_using_existent_reversed_path() {
        Route route = RouteMother.random();
        shouldNotExists(route);
        shouldExists(RouteMother.reverse(route));

        creator.create(route.id(), route.departurePort(), route.arrivalPort());

        shouldHaveSaved(route);
    }

    @Test
    void do_nothing_if_route_already_exists() {
        Route route = RouteMother.random();
        shouldExists(route);

        creator.create(route.id(), route.departurePort(), route.arrivalPort());

        shouldNotHaveSaved(route);
    }
}
