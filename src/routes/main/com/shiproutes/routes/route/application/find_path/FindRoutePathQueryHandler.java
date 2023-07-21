package com.shiproutes.routes.route.application.find_path;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class FindRoutePathQueryHandler implements QueryHandler<FindRoutePathQuery, RoutePathResponse> {

    private final RoutePathFinder finder;

    public FindRoutePathQueryHandler(RoutePathFinder finder) {
        this.finder = finder;
    }

    @Override
    public RoutePathResponse handle(FindRoutePathQuery query) {
        return finder.find(
            new PortId(query.originPort()),
            new PortId(query.destinationPort())
        );
    }
}
