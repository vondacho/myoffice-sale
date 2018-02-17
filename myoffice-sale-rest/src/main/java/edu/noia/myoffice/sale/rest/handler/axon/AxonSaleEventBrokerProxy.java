package edu.noia.myoffice.sale.rest.handler.axon;

import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartNotFoundEventPayload;
import edu.noia.myoffice.sale.rest.handler.SaleEventBroker;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;

import java.time.Instant;

public class AxonSaleEventBrokerProxy extends SaleEventBroker {

    @EventHandler(payloadType = CartEventPayload.class)
    @Override
    public void onSuccess(CartEventPayload event, @Timestamp Instant timestamp) {
        super.onSuccess(event, timestamp);
    }

    @EventHandler(payloadType = CartNotFoundEventPayload.class)
    @Override
    public void onFailure(CartNotFoundEventPayload event, @Timestamp Instant timestamp) {
        super.onFailure(event, timestamp);
    }
}
