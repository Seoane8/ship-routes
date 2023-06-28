package com.shiproutes.ports.departure.infrastructure.persistence.hibernate;

import com.shiproutes.ports.departure.domain.Departure;
import com.shiproutes.ports.departure.domain.DepartureDate;
import com.shiproutes.ports.departure.domain.DepartureId;
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

@Entity(name = "departure")
@Table(name = "departures")
public final class HibernateDepartureEntity {

    @Id
    private String id;
    private String portId;
    private Double latitude;
    private Double longitude;
    private String shipId;
    private Integer teus;
    private Instant date;

    public HibernateDepartureEntity() {
    }

    public HibernateDepartureEntity(Departure departure) {
        this.id = departure.id().value();
        this.portId = departure.portId().value();
        this.latitude = departure.coordinates().latitude().value();
        this.longitude = departure.coordinates().longitude().value();
        this.shipId = departure.shipId().value();
        this.teus = departure.teus().value();
        this.date = departure.date().value();
    }

    public Departure toEntity() {
        return new Departure(
            new DepartureId(this.id),
            new PortId(this.portId),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ),
            new IMO(this.shipId),
            new Teus(this.teus),
            new DepartureDate(this.date)
        );
    }
}
