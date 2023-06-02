package com.shiproutes.ports.ship.application.find_teus;

import com.shiproutes.shared.domain.IntValueObject;
import com.shiproutes.shared.domain.bus.query.Response;

public final class TeusResponse extends IntValueObject implements Response {
    public TeusResponse(Integer value) {
        super(value);
    }
}
