package edu.noia.myoffice.sale.query.event.axon;

import edu.noia.myoffice.sale.domain.event.CartEventHandler;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;

/**
 * This class is a {@link CartEventPayload} listener which proxies a {@link CartEventHandler} instance
 * Event listener aspect provided by Axon
 * Proxy pattern applied
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonCartEventHandler implements CartEventHandler {

    @NonNull
    CartEventHandler eventHandler;

    @EventHandler
    @Override
    public void created(CartCreatedEventPayload event) {
        eventHandler.created(event);
    }

    @EventHandler
    @Override
    public void itemAdded(ItemAddedToCartEventPayload event) {
        eventHandler.itemAdded(event);
    }

    @EventHandler
    @Override
    public void itemRemoved(ItemRemovedFromCartEventPayload event) {
        eventHandler.itemRemoved(event);
    }

    @EventHandler
    @Override
    public void ordered(CartOrderedEventPayload event) {
        eventHandler.ordered(event);
    }

    @EventHandler
    @Override
    public void invoiced(CartInvoicedEventPayload event) {
        eventHandler.invoiced(event);
    }
}
