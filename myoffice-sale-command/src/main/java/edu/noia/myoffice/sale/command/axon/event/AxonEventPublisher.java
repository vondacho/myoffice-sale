package edu.noia.myoffice.sale.command.axon.event;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

@Component
public class AxonEventPublisher implements EventPublisher {

    @Autowired
    EventBus eventBus;

    @Override
    public void accept(Event event) {
        eventBus.publish(asEventMessage(event));
    }
}
