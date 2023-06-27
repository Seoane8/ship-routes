package com.shiproutes.ports.shared.application;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public final class FindPortQuery implements Query {
    private final String id;

    public FindPortQuery(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindPortQuery)) {
            return false;
        }
        FindPortQuery that = (FindPortQuery) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
