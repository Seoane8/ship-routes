package com.shiproutes.ports.port.application.increment_events;

import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.ports.port.domain.PortRepository;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Service;

@Service
public final class PortEventsIncrementer {

    private final PortRepository repository;

    public PortEventsIncrementer(PortRepository repository) {
        this.repository = repository;
    }

    public void increment(PortId portId) throws PortNotExist {
        Port port = repository.search(portId).orElseThrow(() -> new PortNotExist(portId));
        port.incrementEvents();
        repository.save(port);
    }
}
