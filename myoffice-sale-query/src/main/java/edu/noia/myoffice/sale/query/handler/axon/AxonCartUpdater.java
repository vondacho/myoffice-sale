package edu.noia.myoffice.sale.query.handler.axon;

import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;
import edu.noia.myoffice.sale.query.handler.CartUpdater;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;

/**
 * This class is a {@link CartEventPayload} listener which proxies a {@link CartUpdater} instance
 * Event listener aspect provided by Axon
 * Proxy pattern applied
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonCartUpdater implements CartUpdater {

    @NonNull
    CartUpdater cartUpdater;

    @EventHandler
    @Override
    public void created(CartCreatedEventPayload event) {
        cartUpdater.created(event);
    }

    @EventHandler
    @Override
    public void itemAdded(ItemAddedToCartEventPayload event) {
        cartUpdater.itemAdded(event);
    }

    @EventHandler
    @Override
    public void itemRemoved(ItemRemovedFromCartEventPayload event) {
        cartUpdater.itemRemoved(event);
    }

    @EventHandler
    @Override
    public void ordered(CartOrderedEventPayload event) {
        cartUpdater.ordered(event);
    }

    @EventHandler
    @Override
    public void invoiced(CartInvoicedEventPayload event) {
        cartUpdater.invoiced(event);
    }
}
