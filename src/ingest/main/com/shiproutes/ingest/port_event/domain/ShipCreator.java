package com.shiproutes.ingest.port_event.domain;

import com.shiproutes.shared.domain.IMO;

public interface ShipCreator {

    void create(IMO imo, String shipName, Integer teus);

}
