package com.shiproutes.ports.port_event_month;

import com.shiproutes.ports.PortsContextInfrastructureTestCase;
import com.shiproutes.ports.port_event_month.domain.PortEventsByMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class PortEventsByMonthModuleInfrastructureTestCase extends PortsContextInfrastructureTestCase {
    @Autowired
    @Qualifier("mySqlPortEventsByMonthRepository")
    protected PortEventsByMonthRepository mySqlPortRepository;
}
