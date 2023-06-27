package com.shiproutes.ports.port.application.find;

import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.ports.port.domain.PortRepository;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;

@Service
public final class PortFinder {

    private final PortRepository repository;

    public PortFinder(PortRepository repository) {
        this.repository = repository;
    }

    public PortResponse find(PortId id) {
        return repository.search(id).map(PortResponse::from).orElseThrow(() -> new PortNotExist(id));
    }
}
