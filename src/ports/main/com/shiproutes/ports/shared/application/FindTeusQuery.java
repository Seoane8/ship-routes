package com.shiproutes.ports.shared.application;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public final class FindTeusQuery implements Query {

    private final String imo;

    public FindTeusQuery(String imo) {
        this.imo = imo;
    }

    public String imo() {
        return imo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindTeusQuery)) {
            return false;
        }
        FindTeusQuery that = (FindTeusQuery) o;
        return Objects.equals(imo, that.imo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imo);
    }
}
