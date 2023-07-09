package com.shiproutes.ports.port_event_year;

import com.shiproutes.ports.PortsContextInfrastructureTestCase;
import com.shiproutes.ports.port_event_year.domain.PortEventsByYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class PortEventsByYearModuleInfrastructureTestCase extends PortsContextInfrastructureTestCase {
    @Autowired
    @Qualifier("mySqlPortEventsByYearRepository")
    protected PortEventsByYearRepository mySqlPortRepository;
}
