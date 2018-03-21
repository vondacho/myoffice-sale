package edu.noia.myoffice.sale.command.aggregate.axon;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.vo.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonCart extends Cart {

    @AggregateIdentifier
    CartId aggregateId;

    private AxonCart(CartState cartState) {
        super(cartState);
    }

    public static AxonCart create(CartSpecification specification) {
        AxonCart cart = new AxonCart(CartSample.from(validateBean(specification)));
        AggregateLifecycle.apply(CartCreatedEventPayload.of(cart.getId(), specification));
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
    public void created(CartCreatedEventPayload event, @Timestamp Instant timestamp) {
        aggregateId = event.getCartId();
        super.create(event, timestamp);
    }

    @EventSourcingHandler
    public void itemAdded(ItemAddedToCartEventPayload event, @Timestamp Instant timestamp) {
        super.addItem(event, timestamp);
    }

    @EventSourcingHandler
    public void itemRemoved(ItemRemovedFromCartEventPayload event, @Timestamp Instant timestamp) {
        super.removeItem(event, timestamp);
    }

    @EventSourcingHandler
    public void ordered(CartOrderedEventPayload event, @Timestamp Instant timestamp) {
        super.order(event, timestamp);
    }

    @EventSourcingHandler
    public void invoiced(CartInvoicedEventPayload event, @Timestamp Instant timestamp) {
        super.invoice(event, timestamp);
    }

    @Override
    public Holder<Cart> save(CartRepository repository) {
        return action -> action.accept(this);
    }
}
