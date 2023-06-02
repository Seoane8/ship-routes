package com.shiproutes.ports.port.application.find;

import com.shiproutes.shared.domain.bus.query.Query;

public final class FindPortQuery implements Query {
    private final String id;

    public FindPortQuery(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
