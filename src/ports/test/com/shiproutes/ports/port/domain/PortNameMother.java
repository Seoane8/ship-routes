package com.shiproutes.ports.port.domain;

import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.shared.domain.WordMother;

public final class PortNameMother {
    public static PortName random() {
        return new PortName(WordMother.random());
    }
}
