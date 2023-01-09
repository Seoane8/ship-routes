package com.shiproutes.ports.departure;

import com.shiproutes.ports.PortsContextInfrastructureTestCase;
import com.shiproutes.ports.departure.domain.DepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class DepartureModuleInfrastructureTestCase extends PortsContextInfrastructureTestCase {
    @Autowired
    @Qualifier("mySqlDepartureRepository")
    protected DepartureRepository mySqlDepartureRepository;
}
