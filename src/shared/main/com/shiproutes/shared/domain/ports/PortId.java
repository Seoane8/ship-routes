package com.shiproutes.shared.domain.ports;

import com.shiproutes.shared.domain.Identifier;

public final class PortId extends Identifier {

    public PortId() {
        super();
    }

    public PortId(String value) {
        super(value);
    }

    public static PortId empty() {
        return new PortId();
    }

}
