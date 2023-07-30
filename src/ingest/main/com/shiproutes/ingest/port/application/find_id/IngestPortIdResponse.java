package com.shiproutes.ingest.port.application.find_id;

import com.shiproutes.ingest.port.domain.IngestPort;
import com.shiproutes.shared.domain.bus.query.Response;

import java.util.Objects;

public class IngestPortIdResponse implements Response {

    private final String portId;

    public IngestPortIdResponse(String portId) {
        this.portId = portId;
    }

    public static IngestPortIdResponse fromEntity(IngestPort port) {
        return new IngestPortIdResponse(port.id().value());
    }

    public String portId() {
        return portId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngestPortIdResponse)) return false;
        IngestPortIdResponse that = (IngestPortIdResponse) o;
        return Objects.equals(portId, that.portId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portId);
    }
}
