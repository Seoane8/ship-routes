package com.shiproutes.ports.port;

import com.shiproutes.ports.PortsContextInfrastructureTestCase;
import com.shiproutes.ports.port.domain.PortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class PortModuleInfrastructureTestCase extends PortsContextInfrastructureTestCase {
    @Autowired
    @Qualifier("mySqlPortRepository")
    protected PortRepository mySqlPortRepository;
}
