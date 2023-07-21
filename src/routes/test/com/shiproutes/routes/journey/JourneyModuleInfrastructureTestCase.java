package com.shiproutes.routes.journey;

import com.shiproutes.routes.RoutesContextInfrastructureTestCase;
import com.shiproutes.routes.journey.domain.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JourneyModuleInfrastructureTestCase extends RoutesContextInfrastructureTestCase {
    @Autowired
    protected JourneyRepository mySqlJourneyRepository;
}
