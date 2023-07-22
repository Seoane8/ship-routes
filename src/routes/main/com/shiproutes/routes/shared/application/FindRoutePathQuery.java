package com.shiproutes.routes.shared.application;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public class FindRoutePathQuery implements Query {

    private final String originPort;
    private final String destinationPort;

    public FindRoutePathQuery(String originPort, String destinationPort) {
        this.originPort = originPort;
        this.destinationPort = destinationPort;
    }

    public String originPort() {
        return originPort;
    }

    public String destinationPort() {
        return destinationPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FindRoutePathQuery)) return false;
        FindRoutePathQuery that = (FindRoutePathQuery) o;
        return Objects.equals(originPort, that.originPort) && Objects.equals(destinationPort, that.destinationPort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originPort, destinationPort);
    }
}
