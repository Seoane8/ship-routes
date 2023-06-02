package com.shiproutes.ports.ship.infrastructure.persistence.hibernate;

import com.shiproutes.ports.ship.domain.PortShip;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Teus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ship")
@Table(name = "ships")
public final class HibernatePortShipEntity {

    @Id
    private String imo;

    private Integer teus;

    public HibernatePortShipEntity() {
    }

    public HibernatePortShipEntity(PortShip port) {
        this.imo = port.imo().value();
        this.teus = port.teus().value();
    }

    public PortShip toEntity() {
        return new PortShip(
            new IMO(this.imo),
            new Teus(this.teus)
        );
    }

}
