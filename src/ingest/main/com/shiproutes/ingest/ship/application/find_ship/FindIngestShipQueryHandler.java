package com.shiproutes.ingest.ship.application.find_ship;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public final class FindIngestShipQueryHandler implements QueryHandler<FindIngestShipQuery, IngestShipResponse> {

    private final IngestShipFinder finder;

    public FindIngestShipQueryHandler(IngestShipFinder finder) {
        this.finder = finder;
    }

    @Override
    public IngestShipResponse handle(FindIngestShipQuery query) {
        return finder.find(new IMO(query.imo()));
    }
}
