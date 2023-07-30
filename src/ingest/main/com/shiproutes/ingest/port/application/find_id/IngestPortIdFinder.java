package com.shiproutes.ingest.port.application.find_id;

import com.shiproutes.ingest.port.domain.IngestPortRepository;
import com.shiproutes.ingest.port.domain.PortNotExist;
import com.shiproutes.shared.domain.Service;

@Service
public class IngestPortIdFinder {

    private final IngestPortRepository repository;

    public IngestPortIdFinder(IngestPortRepository repository) {
        this.repository = repository;
    }

    public IngestPortIdResponse find(String locode) {
        return repository.searchByLocode(locode).map(IngestPortIdResponse::fromEntity)
            .orElseThrow(() -> new PortNotExist(locode));
    }
}
