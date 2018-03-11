package edu.noia.myoffice.sale.messaging.adapter.axon;

import edu.noia.myoffice.common.domain.event.EventPayload;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventBus;

import java.util.function.Consumer;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

@RequiredArgsConstructor
public class EventPublisherAdapter implements Consumer<EventPayload> {

    @NonNull
    EventBus eventBus;

    @Override
    public void accept(EventPayload payload) {
        eventBus.publish(asEventMessage(payload));
    }
}
