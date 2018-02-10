package edu.noia.myoffice.sale.common.listener;

import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;

public interface CartEventListener {

    void created(CartCreatedEvent event);

    void itemAdded(ItemAddedToCartEvent event);

    void itemRemoved(ItemRemovedFromCartEvent event);

    void ordered(CartOrderedEvent event);

    void invoiced(CartInvoicedEvent event);
}
