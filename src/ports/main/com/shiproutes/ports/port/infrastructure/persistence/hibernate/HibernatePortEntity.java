package com.shiproutes.ports.port.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port.domain.Locode;
import com.shiproutes.ports.port.domain.Port;
import com.shiproutes.ports.port.domain.PortId;
import com.shiproutes.ports.port.domain.PortName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "port")
@Table(name = "ports")
public final class HibernatePortEntity {

    @Id
    private String id;
    private String name;
    private String locode;

    public HibernatePortEntity() {
    }

    public HibernatePortEntity(Port port) {
        this.id = port.id().value();
        this.name = port.name().value();
        this.locode = port.locode().value();
    }

    public Port toEntity() {
        return new Port(
            new PortId(this.id),
            new PortName(this.name),
            new Locode(this.locode)
        );
    }
}
