package edu.noia.myoffice.sale.command.aggregate.axon;

import edu.noia.myoffice.common.domain.event.EventPayload;
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
import java.util.function.Consumer;

@EqualsAndHashCode(callSuper = true)
@Aggregate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonCart extends Cart {

    @AggregateIdentifier
    CartId aggregateId;

    private AxonCart(CartState state) {
        super(state);
    }

    public static AxonCart create(CartSpecification specification) {
        return (AxonCart) create(specification, AggregateLifecycle::apply, AxonCart::new);
    }

    @Override
    public void addItem(CartItem cartItem, Consumer<EventPayload> eventPublisher) {
        super.addItem(cartItem, AggregateLifecycle::apply);
    }

    @Override
    public CartItem removeItem(CartItemId itemId, Consumer<EventPayload> eventPublisher) {
        return super.removeItem(itemId, AggregateLifecycle::apply);
    }

    @Override
    public void order(Consumer<EventPayload> eventPublisher) {
        super.order(AggregateLifecycle::apply);
    }

    @Override
    public void close(InvoiceId invoiceId, Consumer<EventPayload> eventPublisher) {
        super.close(invoiceId, AggregateLifecycle::apply);
    }

    @EventSourcingHandler
    @Override
    public void created(CartCreatedEventPayload event, @Timestamp Instant timestamp) {
        aggregateId = event.getCartId();
        super.created(event, timestamp);
    }

    @EventSourcingHandler
    @Override
    public void itemAdded(ItemAddedToCartEventPayload event, @Timestamp Instant timestamp) {
        super.itemAdded(event, timestamp);
    }

    @EventSourcingHandler
    @Override
    public void itemRemoved(ItemRemovedFromCartEventPayload event, @Timestamp Instant timestamp) {
        super.itemRemoved(event, timestamp);
    }

    @EventSourcingHandler
    @Override
    public void ordered(CartOrderedEventPayload event, @Timestamp Instant timestamp) {
        super.ordered(event, timestamp);
    }

    @EventSourcingHandler
    @Override
    public void invoiced(CartInvoicedEventPayload event, @Timestamp Instant timestamp) {
        super.invoiced(event, timestamp);
    }

    @Override
    public Holder<Cart> save(CartRepository repository) {
        return action -> action.accept(this);
    }
}
