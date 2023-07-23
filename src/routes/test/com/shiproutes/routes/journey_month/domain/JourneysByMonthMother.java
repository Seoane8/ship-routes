package com.shiproutes.routes.journey_month.domain;

import com.shiproutes.routes.shared.domain.RoutePathMother;
import com.shiproutes.shared.domain.MonthMother;
import com.shiproutes.shared.domain.YearMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public class JourneysByMonthMother {

    public static JourneysByMonth random() {
        return new JourneysByMonth(
            JourneysByMonthIdMother.random(),
            PortIdMother.random(),
            PortIdMother.random(),
            RoutePathMother.random(),
            MonthMother.random(),
            YearMother.random(),
            JourneysCounterMother.random()
        );
    }

}
