package com.shiproutes.ports.port.application.increment_events;

import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.ports.port.domain.PortRepository;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.ports.PortId;

@Service
public final class PortEventsIncrementer {

    private final PortRepository repository;

    public PortEventsIncrementer(PortRepository repository) {
        this.repository = repository;
    }

    public void increment(PortId portId, PortEventType type, Teus teus) throws PortNotExist {
        Port port = repository.search(portId).orElseThrow(() -> new PortNotExist(portId));

        if (type == PortEventType.DEPARTURE) {
            port.incrementDepartures();
        } else {
            port.incrementArrivals();
        }
        port.incrementTeus(teus);

        repository.save(port);
    }
}
