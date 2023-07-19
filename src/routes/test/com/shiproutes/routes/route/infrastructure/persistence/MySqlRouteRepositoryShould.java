package com.shiproutes.routes.route.infrastructure.persistence;

import com.shiproutes.routes.route.RouteModuleInfrastructureTestCase;
import com.shiproutes.routes.route.domain.Route;
import com.shiproutes.routes.route.domain.RouteIdMother;
import com.shiproutes.routes.route.domain.RouteMother;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class MySqlRouteRepositoryShould extends RouteModuleInfrastructureTestCase {

    @Test
    void save_a_route() {
        Route route = RouteMother.random();

        mySqlRouteRepository.save(route);
    }

    @Test
    void return_an_existing_route() {
        Route route = RouteMother.random();
        mySqlRouteRepository.save(route);

        assertEquals(Optional.of(route), mySqlRouteRepository.search(route.id()));
    }

    @Test
    void not_return_a_non_existent_route() {
        assertEquals(Optional.empty(), mySqlRouteRepository.search(RouteIdMother.random()));
    }
}
