package com.shiproutes.ports.port.application.create;

import com.shiproutes.ports.port.domain.*;
import com.shiproutes.shared.domain.Service;

@Service
public final class PortCreator {

    private final PortRepository repository;

    public PortCreator(PortRepository repository) {
        this.repository = repository;
    }

    public void create(PortId id, PortName name, Locode locode, Coordinates coordinates) throws PortAlreadyExists {
        ensurePortNotExist(id);

        Port port = new Port(id, name, locode, coordinates);

        repository.save(port);
    }

    private void ensurePortNotExist(PortId id) throws PortAlreadyExists {
        if (repository.search(id).isPresent()) throw new PortAlreadyExists(id);
    }


}
