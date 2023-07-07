package com.shiproutes.ports.port_event;

import com.shiproutes.ports.PortsContextInfrastructureTestCase;
import com.shiproutes.ports.port_event.domain.PortEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class PortEventModuleInfrastructureTestCase extends PortsContextInfrastructureTestCase {
    @Autowired
    @Qualifier("mySqlPortEventRepository")
    protected PortEventRepository mySqlPortEventRepository;
}
