package edu.noia.myoffice.sale.query.adapter;

import edu.noia.myoffice.sale.common.listener.CartEventListener;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.query.listener.CartUpdater;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartUpdaterListenerAdapter implements CartEventListener {

    @NonNull
    CartUpdater cartUpdater;

    @EventHandler(payloadType = CartCreatedEvent.class)
    @Override
    public void created(CartCreatedEvent event) {
        cartUpdater.created(event);
    }

    @EventHandler(payloadType = ItemAddedToCartEvent.class)
    @Override
    public void itemAdded(ItemAddedToCartEvent event) {
        cartUpdater.itemAdded(event);
    }

    @EventHandler(payloadType = ItemRemovedFromCartEvent.class)
    @Override
    public void itemRemoved(ItemRemovedFromCartEvent event) {
        cartUpdater.itemRemoved(event);
    }

    @EventHandler(payloadType = CartOrderedEvent.class)
    @Override
    public void ordered(CartOrderedEvent event) {
        cartUpdater.ordered(event);
    }

    @EventHandler(payloadType = CartInvoicedEvent.class)
    @Override
    public void invoiced(CartInvoicedEvent event) {
        cartUpdater.invoiced(event);
    }
}
