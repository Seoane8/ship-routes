package com.shiproutes.ports.arrival.infrastructure.persistence.hibernate;

import com.shiproutes.ports.arrival.domain.Arrival;
import com.shiproutes.ports.arrival.domain.ArrivalDate;
import com.shiproutes.ports.arrival.domain.ArrivalId;
import com.shiproutes.ports.shared.domain.Coordinates;
import com.shiproutes.ports.shared.domain.Latitude;
import com.shiproutes.ports.shared.domain.Longitude;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.PortId;
import com.shiproutes.shared.domain.Teus;

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
    private Double latitude;
    private Double longitude;
    private String shipId;
    private Integer teus;
    private Instant date;

    public HibernateArrivalEntity() {
    }

    public HibernateArrivalEntity(Arrival arrival) {
        this.id = arrival.id().value();
        this.portId = arrival.portId().value();
        this.latitude = arrival.coordinates().latitude().value();
        this.longitude = arrival.coordinates().longitude().value();
        this.shipId = arrival.shipId().value();
        this.teus = arrival.teus().value();
        this.date = arrival.date().value();
    }

    public Arrival toEntity() {
        return new Arrival(
            new ArrivalId(this.id),
            new PortId(this.portId),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ),
            new IMO(this.shipId),
            new Teus(this.teus),
            new ArrivalDate(this.date)
        );
    }
}
