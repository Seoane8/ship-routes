package com.shiproutes.ports.port.application.find_by_locode;

import com.shiproutes.ports.port.domain.Locode;
import com.shiproutes.ports.port.domain.PortNotExist;
import com.shiproutes.ports.port.domain.PortRepository;
import com.shiproutes.ports.shared.application.PortResponse;
import com.shiproutes.shared.domain.Service;

@Service
public class PortByLocodeFinder {

    private final PortRepository repository;

    public PortByLocodeFinder(PortRepository repository) {
        this.repository = repository;
    }

    public PortResponse find(Locode locode) {
        return repository.searchByLocode(locode).map(PortResponse::from).orElseThrow(() -> new PortNotExist(locode));
    }
}
