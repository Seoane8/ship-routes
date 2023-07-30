package com.shiproutes.ingest.ship.infrastructure.persistence.hibernate;

import com.shiproutes.ingest.ship.domain.IngestShip;
import com.shiproutes.shared.domain.IMO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ingestShip")
@Table(name = "ships")
public final class HibernateIngestShipEntity {

    @Id
    private String imo;

    public HibernateIngestShipEntity() {
    }

    public HibernateIngestShipEntity(IngestShip ship) {
        this.imo = ship.imo().value();
    }

    public IngestShip toEntity() {
        return new IngestShip(
            new IMO(this.imo)
        );
    }

}
