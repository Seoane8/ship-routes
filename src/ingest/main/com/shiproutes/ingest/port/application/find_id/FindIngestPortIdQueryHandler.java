package com.shiproutes.ingest.port.application.find_id;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.query.QueryHandler;

@Service
public class FindIngestPortIdQueryHandler implements QueryHandler<FindIngestPortIdQuery, IngestPortIdResponse> {

    private final IngestPortIdFinder finder;

    public FindIngestPortIdQueryHandler(IngestPortIdFinder finder) {
        this.finder = finder;
    }

    @Override
    public IngestPortIdResponse handle(FindIngestPortIdQuery query) {
        return finder.find(query.locode());
    }
}
