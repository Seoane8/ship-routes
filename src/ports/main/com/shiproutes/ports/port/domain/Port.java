package com.shiproutes.ports.port.domain;

import java.util.Objects;

public final class Port {
    private final PortId id;
    private final PortName name;
    private final Locode locode;

    public Port(PortId id, PortName name, Locode locode) {
        this.id = id;
        this.name = name;
        this.locode = locode;
    }

    public PortId id() {
        return id;
    }

    public PortName name() {
        return name;
    }

    public Locode locode() {
        return locode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Port)) return false;
        Port port = (Port) o;
        return Objects.equals(id, port.id) && Objects.equals(name, port.name) && Objects.equals(locode, port.locode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, locode);
    }
}
