package edu.noia.myoffice.sale.ui.handler;

import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.ui.handler.SaleEventHandler;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class SaleEventHandlerAdapter extends SaleEventHandler {

    @EventHandler
    public void on(CartCreatedEvent event) {
        super.on(event);
    }

    @EventHandler
    public void on(ItemAddedToCartEvent event) {
        super.on(event);
    }

    @PreDestroy
    public void terminate() {
        super.terminate();
    }

}
