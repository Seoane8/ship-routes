package com.shiproutes.routes.journey_month;

import com.shiproutes.routes.RoutesContextInfrastructureTestCase;
import com.shiproutes.routes.journey_month.domain.JourneysByMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JourneysByMonthModuleInfrastructureTestCase extends RoutesContextInfrastructureTestCase {

    @Autowired
    protected JourneysByMonthRepository mySqlJourneysByMonthRepository;
}
