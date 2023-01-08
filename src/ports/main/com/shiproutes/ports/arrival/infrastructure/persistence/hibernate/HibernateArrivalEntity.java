package com.shiproutes.ports.arrival.infrastructure.persistence.hibernate;

import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalDate;
import com.shiproutes.ports.arrival.domain.ArrivalId;
import com.shiproutes.ports.shared.domain.PortId;
import com.shiproutes.shared.domain.IMO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity(name = "arrival")
@Table(name = "arrivals")
public final class HibernateArrivalEntity {

    @Id
    private String id;
    private String portId;
    private String shipId;
    private Instant date;

    public HibernateArrivalEntity() {
    }

    public HibernateArrivalEntity(Arrival arrival) {
        this.id = arrival.id().value();
        this.portId = arrival.portId().value();
        this.shipId = arrival.shipId().value();
        this.date = arrival.date().value();
    }

    public Arrival toEntity() {
        return new Arrival(
            new ArrivalId(this.id),
            new PortId(this.portId),
            new IMO(this.shipId),
            new ArrivalDate(this.date)
        );
    }
}
