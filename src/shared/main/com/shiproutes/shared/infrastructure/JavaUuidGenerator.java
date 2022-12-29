package com.shiproutes.shared.infrastructure;

import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.UuidGenerator;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
