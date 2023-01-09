package com.shiproutes.ports.arrival;

import com.shiproutes.ports.PortsContextInfrastructureTestCase;
import com.shiproutes.ports.arrival.domain.ArrivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class ArrivalModuleInfrastructureTestCase extends PortsContextInfrastructureTestCase {
    @Autowired
    @Qualifier("mySqlArrivalRepository")
    protected ArrivalRepository mySqlArrivalRepository;
}
