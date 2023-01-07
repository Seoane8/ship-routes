package com.shiproutes.ships.ship.infrastructure.persistence.hibernate;

import com.shiproutes.shared.domain.IMO;
import com.shiproutes.ships.ship.domain.Ship;
import com.shiproutes.ships.ship.domain.ShipName;
import com.shiproutes.ships.ship.domain.Teus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ship")
@Table(name = "ships")
public final class HibernateShipEntity {

    @Id
    private String imo;

    private String name;

    private Integer teus;

    public HibernateShipEntity() {
    }

    public HibernateShipEntity(Ship ship) {
        this.imo = ship.imo().value();
        this.name = ship.name().value();
        this.teus = ship.teus().value();
    }

    public Ship toEntity() {
        return new Ship(
            new IMO(this.imo),
            new ShipName(this.name),
            new Teus(this.teus)
        );
    }

}
