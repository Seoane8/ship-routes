package com.shiproutes.ingest.port_event.domain;

import com.shiproutes.ingest.port_event.application.ingest.IngestPortEventCommand;
import com.shiproutes.shared.domain.*;
import com.shiproutes.shared.domain.ports.CoordinatesMother;

public class IngestPortEventCommandMother {
    public static IngestPortEventCommand random() {
        return new IngestPortEventCommand(
            WordMother.random(),
            WordMother.random(),
            CoordinatesMother.random().longitude().value(),
            CoordinatesMother.random().latitude().value(),
            IMOMother.random().value(),
            WordMother.random(),
            TeusMother.random().value(),
            InstantMother.random(),
            MotherCreator.random().options().option(PortEventType.class).name()
        );
    }
}
