package edu.noia.myoffice.sale.command.axon.aggregate;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.aggregate.ESCart;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.domain.vo.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonESCart extends ESCart {

    @AggregateIdentifier
    CartId aggregateId;

    private AxonESCart(CartMutableState cartState) {
        super(cartState);
    }

    public static AxonESCart of(CartState cartState) {
        validate(cartState);
        AxonESCart cart = new AxonESCart();
        cart.identify();
        AggregateLifecycle.apply(CartCreatedEvent.of(cart.getId(), CartSample.of(cartState)));
        return cart;
    }

    @Override
    public void addItem(CartItem cartItem, EventPublisher eventPublisher) {
        super.addItem(cartItem, AggregateLifecycle::apply);
    }

    @Override
    public CartItem removeItem(CartItemId itemId, EventPublisher eventPublisher) {
        return super.removeItem(itemId, AggregateLifecycle::apply);
    }

    @Override
    public void order(EventPublisher eventPublisher) {
        super.order(AggregateLifecycle::apply);
    }

    @Override
    public void close(InvoiceId invoiceId, EventPublisher eventPublisher) {
        super.close(invoiceId, AggregateLifecycle::apply);
    }

    @EventSourcingHandler
    @Override
    public void created(CartCreatedEvent event) {
        aggregateId = event.getCartId();
        super.created(event);
    }

    @EventSourcingHandler
    @Override
    public void itemAdded(ItemAddedToCartEvent event) {
        super.itemAdded(event);
    }

    @EventSourcingHandler
    @Override
    public void itemRemoved(ItemRemovedFromCartEvent event) {
        super.itemRemoved(event);
    }

    @EventSourcingHandler
    @Override
    public void ordered(CartOrderedEvent event) {
        super.ordered(event);
    }

    @EventSourcingHandler
    @Override
    public void invoiced(CartInvoicedEvent event) {
        super.invoiced(event);
    }
}
