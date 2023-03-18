package com.shiproutes.ports.departure.infrastructure.persistence.hibernate;

import com.shiproutes.ports.departure.domain.Departure;
import com.shiproutes.ports.departure.domain.DepartureDate;
import com.shiproutes.ports.departure.domain.DepartureId;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity(name = "departure")
@Table(name = "departures")
public final class HibernateDepartureEntity {

    @Id
    private String id;
    private String portId;
    private String shipId;
    private Instant date;

    public HibernateDepartureEntity() {
    }

    public HibernateDepartureEntity(Departure departure) {
        this.id = departure.id().value();
        this.portId = departure.portId().value();
        this.shipId = departure.shipId().value();
        this.date = departure.date().value();
    }

    public Departure toEntity() {
        return new Departure(
          new DepartureId(this.id),
          new PortId(this.portId),
          new IMO(this.shipId),
          new DepartureDate(this.date)
        );
    }
}
