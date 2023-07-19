package com.shiproutes.routes.port.application.find_coordinates;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public class FindCoordinatesQuery implements Query {

    private final String portId;

    public FindCoordinatesQuery(String portId) {
        this.portId = portId;
    }

    public String portId() {
        return portId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FindCoordinatesQuery)) return false;
        FindCoordinatesQuery that = (FindCoordinatesQuery) o;
        return Objects.equals(portId, that.portId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portId);
    }
}
