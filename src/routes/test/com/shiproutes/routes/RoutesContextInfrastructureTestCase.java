package com.shiproutes.routes;

import com.shiproutes.apps.routes.backend.RoutesApplication;
import com.shiproutes.shared.infrastructure.InfrastructureTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = RoutesApplication.class)
@SpringBootTest
public abstract class RoutesContextInfrastructureTestCase extends InfrastructureTestCase {
}
