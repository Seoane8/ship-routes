package com.shiproutes.ports.port_event.domain;

import com.shiproutes.shared.domain.DomainError;

public class StartDateAfterEndDate extends DomainError {
    public StartDateAfterEndDate(PortEventDate startDate, PortEventDate endDate) {
        super("start_date_after_end_date",
            String.format("The start date <%s> is after the end date <%s>", startDate, endDate));
    }
}
