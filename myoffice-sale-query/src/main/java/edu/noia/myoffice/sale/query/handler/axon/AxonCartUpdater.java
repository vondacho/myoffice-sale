package edu.noia.myoffice.sale.query.handler.axon;

import edu.noia.myoffice.sale.data.adapter.CartRepositoryAdapter;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.query.handler.CartUpdater;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AxonCartUpdater extends CartUpdater {

    public AxonCartUpdater(CartRepositoryAdapter repository) {
        super(repository);
    }

    @EventHandler
    public void on(ItemAddedToCartEvent event) {
        super.on(event);
    }

    @EventHandler
    public void on(ItemRemovedFromCartEvent event) {
        super.on(event);
    }

    @EventHandler
    public void on(CartOrderedEvent event) {
        super.on(event);
    }

    @EventHandler
    public void on(CartInvoicedEvent event) {
        super.on(event);
    }
}
