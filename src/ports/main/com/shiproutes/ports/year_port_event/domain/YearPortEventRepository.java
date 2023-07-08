package com.shiproutes.ports.year_port_event.domain;

import java.util.Set;

public interface YearPortEventRepository {

    Set<YearPortEvent> searchAll();

}
