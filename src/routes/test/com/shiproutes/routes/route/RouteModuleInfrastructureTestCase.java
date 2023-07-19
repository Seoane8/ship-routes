package com.shiproutes.routes.route;

import com.shiproutes.routes.RoutesContextInfrastructureTestCase;
import com.shiproutes.routes.route.domain.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RouteModuleInfrastructureTestCase extends RoutesContextInfrastructureTestCase {
    @Autowired
    protected RouteRepository mySqlRouteRepository;
}
