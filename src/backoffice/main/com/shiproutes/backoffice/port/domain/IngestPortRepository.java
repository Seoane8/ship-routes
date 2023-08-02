package com.shiproutes.backoffice.port.domain;

import java.util.Optional;

public interface IngestPortRepository {

    void save(IngestPort ingestPort);

    Optional<IngestPort> searchByLocode(String locode);

}
