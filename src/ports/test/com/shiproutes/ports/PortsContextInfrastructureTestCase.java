package com.shiproutes.ports;

import com.shiproutes.apps.ports.backend.PortsApplication;
import com.shiproutes.shared.infrastructure.InfrastructureTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = PortsApplication.class)
@SpringBootTest
public abstract class PortsContextInfrastructureTestCase extends InfrastructureTestCase {
}
