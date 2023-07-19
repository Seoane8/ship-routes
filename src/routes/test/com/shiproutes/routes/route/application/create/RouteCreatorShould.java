package com.shiproutes.routes.route.application.create;

import com.shiproutes.routes.route.RouteModuleUnitTestCase;
import com.shiproutes.routes.route.domain.PortNotExist;
import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteAlreadyExists;
import com.shiproutes.routes.route.domain.RouteMother;
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
