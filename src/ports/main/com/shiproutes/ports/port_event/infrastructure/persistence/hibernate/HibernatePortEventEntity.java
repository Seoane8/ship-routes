package com.shiproutes.ports.port_event.infrastructure.persistence.hibernate;

import com.shiproutes.ports.port_event.domain.PortEvent;
import com.shiproutes.ports.port_event.domain.PortEventDate;
import com.shiproutes.ports.port_event.domain.PortEventId;
import com.shiproutes.ports.port_event.domain.PortEventType;
import com.shiproutes.ports.shared.domain.PortName;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Teus;
import com.shiproutes.shared.domain.ports.Coordinates;
import com.shiproutes.shared.domain.ports.Latitude;
import com.shiproutes.shared.domain.ports.Longitude;
import com.shiproutes.shared.domain.ports.PortId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity(name = "portEvent")
@Table(name = "port_events")
public final class HibernatePortEventEntity {

    @Id
    private String id;
    private String type;
    private String portId;
    private String portName;
    private Double latitude;
    private Double longitude;
    private String shipId;
    private Integer teus;
    private Instant date;

    public HibernatePortEventEntity() {
    }

    public HibernatePortEventEntity(PortEvent portEvent) {
        this.id = portEvent.id().value();
        this.type = portEvent.type().value();
        this.portId = portEvent.portId().value();
        this.portName = portEvent.portName().value();
        this.latitude = portEvent.coordinates().latitude().value();
        this.longitude = portEvent.coordinates().longitude().value();
        this.shipId = portEvent.shipId().value();
        this.teus = portEvent.teus().value();
        this.date = portEvent.date().value();
    }

    public PortEvent toEntity() {
        return new PortEvent(
            new PortEventId(this.id),
            PortEventType.valueOf(type),
            new PortId(this.portId),
            new PortName(this.portName),
            new Coordinates(
                new Latitude(this.latitude),
                new Longitude(this.longitude)
            ),
            new IMO(this.shipId),
            new Teus(this.teus),
            new PortEventDate(this.date)
        );
    }
}
