package com.shiproutes.backoffice.port.application.find_id;

import com.shiproutes.shared.domain.bus.query.Query;

import java.util.Objects;

public class FindIngestPortIdQuery implements Query {

    private final String locode;

    public FindIngestPortIdQuery(String locode) {
        this.locode = locode;
    }

    public String locode() {
        return locode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FindIngestPortIdQuery)) return false;
        FindIngestPortIdQuery that = (FindIngestPortIdQuery) o;
        return Objects.equals(locode, that.locode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locode);
    }
}
