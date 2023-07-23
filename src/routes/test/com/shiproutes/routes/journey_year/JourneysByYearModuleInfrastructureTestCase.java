package com.shiproutes.routes.journey_year;

import com.shiproutes.routes.RoutesContextInfrastructureTestCase;
import com.shiproutes.routes.journey_year.domain.JourneysByYearRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JourneysByYearModuleInfrastructureTestCase extends RoutesContextInfrastructureTestCase {

    @Autowired
    protected JourneysByYearRepository mySqlJourneysByYearRepository;
}
