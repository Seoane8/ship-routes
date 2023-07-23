package com.shiproutes.shared.domain;

public class StartDateAfterEndDate extends DomainError {
    public StartDateAfterEndDate(InstantValueObject startDate, InstantValueObject endDate) {
        super("start_date_after_end_date",
            String.format("The start date <%s> is after the end date <%s>", startDate, endDate));
    }
}
