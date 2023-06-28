package com.shiproutes.ports.ship.application.find_teus;

import com.shiproutes.ports.shared.application.TeusResponse;
import com.shiproutes.ports.ship.domain.PortShip;
import com.shiproutes.ports.ship.domain.PortShipRepository;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;

@Service
public final class PortShipTeusFinder {

    private final PortShipRepository repository;

    public PortShipTeusFinder(PortShipRepository repository) {
        this.repository = repository;
    }

    public TeusResponse find(IMO imo) {
        return repository.search(imo).map(PortShip::teus).map(TeusResponse::fromEntity).orElseThrow();
    }
}
