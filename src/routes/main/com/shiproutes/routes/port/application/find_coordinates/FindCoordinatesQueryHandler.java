package com.shiproutes.routes.port.application.find_coordinates;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.PortId;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class FindCoordinatesQueryHandler implements QueryHandler<FindCoordinatesQuery, CoordinatesResponse> {

    private final RoutePortCoordinatesFinder finder;

    public FindCoordinatesQueryHandler(RoutePortCoordinatesFinder finder) {
        this.finder = finder;
    }

    @Override
    public CoordinatesResponse handle(FindCoordinatesQuery query) {
        return finder.find(new PortId(query.portId()));
    }
}
