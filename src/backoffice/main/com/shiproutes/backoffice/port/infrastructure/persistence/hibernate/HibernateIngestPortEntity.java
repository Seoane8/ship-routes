package com.shiproutes.backoffice.port.infrastructure.persistence.hibernate;

import com.shiproutes.backoffice.port.domain.IngestPort;
import com.shiproutes.shared.domain.ports.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ingestPort")
@Table(name = "ports")
public final class HibernateIngestPortEntity {

    @Id
    private String id;
    private String locode;

    public HibernateIngestPortEntity() {
    }

    public HibernateIngestPortEntity(IngestPort port) {
        this.id = port.id().value();
        this.locode = port.locode();
    }

    public IngestPort toEntity() {
        return new IngestPort(
            new PortId(id),
            locode
        );
    }

}
