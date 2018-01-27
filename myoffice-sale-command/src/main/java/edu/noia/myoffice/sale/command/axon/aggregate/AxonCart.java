package edu.noia.myoffice.sale.command.axon.aggregate;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.util.Holder;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.domain.repository.command.CartRepository;
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
public class AxonCart extends Cart {

    @AggregateIdentifier
    CartId aggregateId;

    private AxonCart(CartMutableState cartState) {
        super(cartState);
    }

    public static AxonCart of(CartState cartState) {
        validate(cartState);
        AxonCart cart = new AxonCart();
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
    public void create(CartCreatedEvent event) {
        aggregateId = event.getCartId();
        super.create(event);
    }

    @EventSourcingHandler
    @Override
    public void addItem(ItemAddedToCartEvent event) {
        super.addItem(event);
    }

    @EventSourcingHandler
    @Override
    public void removeItem(ItemRemovedFromCartEvent event) {
        super.removeItem(event);
    }

    @EventSourcingHandler
    @Override
    public void order(CartOrderedEvent event) {
        super.order(event);
    }

    @EventSourcingHandler
    @Override
    public void invoice(CartInvoicedEvent event) {
        super.invoice(event);
    }

    @Override
    public Holder<Cart> save(CartRepository repository) {
        return action -> action.accept(this);
    }
}
