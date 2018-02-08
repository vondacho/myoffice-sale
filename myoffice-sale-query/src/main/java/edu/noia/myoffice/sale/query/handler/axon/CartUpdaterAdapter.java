package edu.noia.myoffice.sale.query.handler.axon;

import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.query.handler.CartUpdater;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartUpdaterAdapter extends CartUpdater {

    public CartUpdaterAdapter(@Autowired CartStateRepository repository) {
        super(repository);
    }

    @EventHandler(payloadType = CartCreatedEvent.class)
    public void on(CartCreatedEvent event) {
        super.on(event);
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
