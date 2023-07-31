package com.shiproutes.ports.port_event.domain;

import com.shiproutes.ports.port.domain.PortNameMother;
import com.shiproutes.shared.domain.IMOMother;
import com.shiproutes.shared.domain.TeusMother;
import com.shiproutes.shared.domain.ports.CoordinatesMother;
import com.shiproutes.shared.domain.ports.PortIdMother;

public final class PortEventMother {

    public static PortEvent random() {
        return new PortEvent(
            PortEventIdMother.random(),
            PortEventTypeMother.random(),
            PortIdMother.random(),
            PortNameMother.random(),
            CoordinatesMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortEventDateMother.random()
        );
    }

    public static PortEvent randomDeparture() {
        return new PortEvent(
            PortEventIdMother.random(),
            PortEventType.DEPARTURE,
            PortIdMother.random(),
            PortNameMother.random(),
            CoordinatesMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortEventDateMother.random()
        );
    }

    public static PortEvent randomArrival() {
        return new PortEvent(
            PortEventIdMother.random(),
            PortEventType.ARRIVAL,
            PortIdMother.random(),
            PortNameMother.random(),
            CoordinatesMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortEventDateMother.random()
        );
    }

    public static PortEvent randomWithDateBetween(PortEventDate startDate, PortEventDate endDate) {
        return new PortEvent(
            PortEventIdMother.random(),
            PortEventTypeMother.random(),
            PortIdMother.random(),
            PortNameMother.random(),
            CoordinatesMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortEventDateMother.between(startDate, endDate)
        );
    }

    public static PortEvent randomBefore(PortEventDate date) {
        return new PortEvent(
            PortEventIdMother.random(),
            PortEventTypeMother.random(),
            PortIdMother.random(),
            PortNameMother.random(),
            CoordinatesMother.random(),
            IMOMother.random(),
            TeusMother.random(),
            PortEventDateMother.before(date)
        );
    }
}
