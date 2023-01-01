package com.shiproutes.ships;

import com.shiproutes.apps.ships.backend.ShipsApplication;
import com.shiproutes.shared.infrastructure.InfrastructureTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ShipsApplication.class)
@SpringBootTest
public abstract class ShipsContextInfrastructureTestCase extends InfrastructureTestCase {
}
