package com.shiproutes.ingest.port.application.create;

import com.shiproutes.ingest.port.domain.IngestPort;
import com.shiproutes.ingest.port.domain.IngestPortRepository;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public class IngestPortCreator {

    private final IngestPortRepository repository;

    public IngestPortCreator(IngestPortRepository repository) {
        this.repository = repository;
    }

    public void create(PortId portId, String locode) {
        if (repository.searchByLocode(locode).isPresent()) return;

        IngestPort port = new IngestPort(portId, locode);

        repository.save(port);
    }
}
