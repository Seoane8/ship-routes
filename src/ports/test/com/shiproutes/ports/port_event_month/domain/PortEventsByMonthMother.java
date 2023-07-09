package com.shiproutes.ports.port_event_month.domain;

import com.shiproutes.ports.port_event_year.domain.YearMother;
import com.shiproutes.ports.shared.domain.CoordinatesMother;
import com.shiproutes.ports.shared.domain.PortIdMother;
import com.shiproutes.ports.shared.domain.TotalArrivals;
import com.shiproutes.ports.shared.domain.TotalDepartures;
import com.shiproutes.shared.domain.LongMother;
import com.shiproutes.shared.domain.UuidMother;

public class PortEventsByMonthMother {
    public static PortEventsByMonth random() {
        return new PortEventsByMonth(
            new PortEventsByMonthId(UuidMother.random()),
            PortIdMother.random(),
            CoordinatesMother.random(),
            YearMother.random(),
            MonthMother.random(),
            new TotalDepartures(LongMother.random()),
            new TotalArrivals(LongMother.random())
        );
    }
}
