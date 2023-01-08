package com.shiproutes.ports.arrival.application.create;

import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalDate;
import com.shiproutes.ports.arrival.domain.ArrivalId;
import com.shiproutes.ports.arrival.domain.ArrivalRepository;
import com.shiproutes.ports.shared.domain.PortId;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;

@Service
public final class ArrivalCreator {

    private final ArrivalRepository repository;

    public ArrivalCreator(ArrivalRepository repository) {
        this.repository = repository;
    }

    public void create(ArrivalId id, PortId portId, IMO shipId, ArrivalDate date) {
        Arrival arrival = new Arrival(id, portId, shipId, date);

        repository.save(arrival);
    }
}
