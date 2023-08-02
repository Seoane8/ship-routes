package com.shiproutes.backoffice.port_event.domain;

import com.shiproutes.backoffice.port_event.application.ingest.IngestPortEventCommand;
import com.shiproutes.shared.domain.PortEventIngestedEvent;
import com.shiproutes.shared.domain.UuidMother;

public class PortEventIngestedEventMother {
    public static PortEventIngestedEvent from(IngestPortEventCommand command) {
        return new PortEventIngestedEvent(
            UuidMother.random(),
            UuidMother.random(),
            command.imo(),
            command.timestamp(),
            command.eventType()
        );
    }
}
