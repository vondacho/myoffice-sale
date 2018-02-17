package edu.noia.myoffice.sale.rest.handler.axon;

import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.rest.handler.SaleEventBroker;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;

import java.time.Instant;

import static edu.noia.myoffice.sale.rest.processor.Processors.toSaleEvent;

public class AxonSaleEventBrokerProxy extends SaleEventBroker {

    @EventHandler(payloadType = CartEventPayload.class)
    public void on(CartEventPayload event, @Timestamp Instant timestamp) {
        toSaleEvent(timestamp).apply(event).ifPresent(this::accept);
    }
}
