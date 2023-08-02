package com.shiproutes.backoffice.port.domain;

import com.shiproutes.shared.domain.ports.PortId;

import java.util.Objects;

public class IngestPort {

    private final PortId id;

    private final String locode;

    public IngestPort(PortId id, String locode) {
        this.id = id;
        this.locode = locode;
    }

    public PortId id() {
        return id;
    }

    public String locode() {
        return locode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngestPort)) return false;
        IngestPort that = (IngestPort) o;
        return Objects.equals(id, that.id) && Objects.equals(locode, that.locode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locode);
    }
}
