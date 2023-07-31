package com.shiproutes.shared.domain.ports;

import com.shiproutes.shared.domain.IntValueObject;
import com.shiproutes.shared.domain.Teus;

public class TeusCounter extends IntValueObject {
    public TeusCounter(Integer value) {
        super(value);
    }

    public static TeusCounter initialize() {
        return new TeusCounter(0);
    }

    public TeusCounter increment(Teus teus) {
        return new TeusCounter(value() + teus.value());
    }

    public TeusCounter decrement(Teus teus) {
        return new TeusCounter(value() - teus.value());
    }
}
