package com.shiproutes.ports.port.domain;

import java.util.Objects;

public final class Port {
    private final PortId id;
    private final PortName name;
    private final Locode locode;
    private final Coordinates coordinates;

    public Port(PortId id, PortName name, Locode locode, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.locode = locode;
        this.coordinates = coordinates;
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

    public Coordinates coordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Port)) return false;
        Port port = (Port) o;
        return Objects.equals(id, port.id) && Objects.equals(name, port.name)
            && Objects.equals(locode, port.locode) && Objects.equals(coordinates, port.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, locode, coordinates);
    }

}
