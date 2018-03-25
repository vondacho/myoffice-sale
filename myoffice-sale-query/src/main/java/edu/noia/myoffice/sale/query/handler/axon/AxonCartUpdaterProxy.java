package edu.noia.myoffice.sale.query.handler.axon;

import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;
import edu.noia.myoffice.sale.query.handler.CartUpdater;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import org.axonframework.eventhandling.EventHandler;

public class AxonCartUpdaterProxy extends CartUpdater {

    public AxonCartUpdaterProxy(CartStateRepository repository) {
        super(repository);
    }

    @EventHandler
    public void created(CartCreatedEventPayload event) {
        super.created(event);
    }

    @EventHandler
    public void itemAdded(ItemAddedToCartEventPayload event) {
        super.itemAdded(event);
    }

    @EventHandler
    public void itemRemoved(ItemRemovedFromCartEventPayload event) {
        super.itemRemoved(event);
    }

    @EventHandler
    public void ordered(CartOrderedEventPayload event) {
        super.ordered(event);
    }

    @EventHandler
    public void invoiced(CartInvoicedEventPayload event) {
        super.invoiced(event);
    }
}
