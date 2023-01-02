package com.shiproutes.ports.port.domain;

import com.shiproutes.shared.domain.MotherCreator;

public final class LocodeMother {
    public static Locode random() {
        return new Locode(MotherCreator.random().letterify("?????"));
    }
}
