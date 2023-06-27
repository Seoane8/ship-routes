package com.shiproutes.ports.shared.application;

import com.shiproutes.shared.domain.IntValueObject;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.bus.query.Response;

public final class TeusResponse extends IntValueObject implements Response {
    public TeusResponse(Integer value) {
        super(value);
    }

    public static TeusResponse fromEntity(Teus teus) {
        return new TeusResponse(teus.value());
    }
}
