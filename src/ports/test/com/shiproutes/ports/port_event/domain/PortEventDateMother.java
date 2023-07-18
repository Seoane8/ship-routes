package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.InstantMother;

public final class PortEventDateMother {

    public static PortEventDate random() {
        return new PortEventDate(InstantMother.random());
    }

    public static PortEventDate now() {
        return new PortEventDate(InstantMother.now());
    }

    public static PortEventDate before(PortEventDate endDate) {
        return new PortEventDate(InstantMother.randomBefore(endDate.value()));
    }

    public static PortEventDate between(PortEventDate startDate, PortEventDate endDate) {
        return new PortEventDate(InstantMother.randomBetween(startDate.value(), endDate.value()));
    }
}
