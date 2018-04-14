package edu.noia.myoffice.sale.domain.event;

import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;

public interface CartEventHandler {

    void created(CartCreatedEventPayload event);

    void itemAdded(ItemAddedToCartEventPayload event);

    void itemRemoved(ItemRemovedFromCartEventPayload event);

    void ordered(CartOrderedEventPayload event);

    void invoiced(CartInvoicedEventPayload event);
}
