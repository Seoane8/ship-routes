package com.shiproutes.routes.journey.application.create;

import com.shiproutes.routes.journey.domain.ArrivalDate;
import com.shiproutes.routes.journey.domain.DepartureDate;
import com.shiproutes.routes.journey.domain.JourneyUnlinkedEvent;
import com.shiproutes.shared.domain.IMO;
import com.shiproutes.shared.domain.Service;
import com.shiproutes.shared.domain.bus.event.DomainEventSubscriber;
import com.shiproutes.shared.domain.ports.PortId;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({JourneyUnlinkedEvent.class})
public class CreateJourneyOnJourneyUnlinked {

    private final JourneyFromArrivalCreator fromArrivalCreator;
    private final JourneyFromDepartureCreator fromDepartureCreator;

    public CreateJourneyOnJourneyUnlinked(JourneyFromArrivalCreator fromArrivalCreator,
                                          JourneyFromDepartureCreator fromDepartureCreator) {
        this.fromArrivalCreator = fromArrivalCreator;
        this.fromDepartureCreator = fromDepartureCreator;
    }

    @EventListener
    public void on(JourneyUnlinkedEvent event) {
        if (event.type().equals("ARRIVAL")) onArrival(event);
        if (event.type().equals("DEPARTURE")) onDeparture(event);
    }

    private void onArrival(JourneyUnlinkedEvent event) {
        fromArrivalCreator.create(
            new IMO(event.shipId()),
            new PortId(event.portId()),
            new ArrivalDate(event.date())
        );
    }

    private void onDeparture(JourneyUnlinkedEvent event) {
        fromDepartureCreator.create(
            new IMO(event.shipId()),
            new PortId(event.portId()),
            new DepartureDate(event.date())
        );
    }

}
