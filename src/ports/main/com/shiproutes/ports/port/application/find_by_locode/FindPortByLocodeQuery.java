package com.shiproutes.ports.port.application.find_by_locode;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public class FindPortByLocodeQuery implements Query {

    private final String locode;

    public FindPortByLocodeQuery(String locode) {
        this.locode = locode;
    }

    public String locode() {
        return locode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FindPortByLocodeQuery)) return false;
        FindPortByLocodeQuery that = (FindPortByLocodeQuery) o;
        return Objects.equals(locode, that.locode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locode);
    }
}
