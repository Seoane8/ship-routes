package com.shiproutes.ports.port.application.create;

import com.shiproutes.ports.port.domain.*;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.EventBus;

@Service
public final class PortCreator {

    private final PortRepository repository;
    private final EventBus eventBus;

    public PortCreator(PortRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(PortId id, PortName name, Locode locode, Coordinates coordinates) throws PortAlreadyExists {
        ensurePortNotExist(id);

        Port port = Port.create(id, name, locode, coordinates);

        repository.save(port);
        eventBus.publish(port.pullDomainEvents());
    }

    private void ensurePortNotExist(PortId id) throws PortAlreadyExists {
        if (repository.search(id).isPresent()) throw new PortAlreadyExists(id);
    }


}
