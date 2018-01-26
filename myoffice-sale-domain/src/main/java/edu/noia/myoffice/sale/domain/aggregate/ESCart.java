package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartItemId;
import edu.noia.myoffice.sale.domain.vo.CartMutableSample;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@NoArgsConstructor
public abstract class ESCart extends Cart {

    List<Event> audit = new ArrayList<>();

    public List<Event> getAudit() {
        return Collections.unmodifiableList(audit);
    }

    public ESCart(CartMutableState cartState) {
        super(cartState);
    }

    @Override
    public void addItem(CartItem cartItem, EventPublisher eventPublisher) {
        validate(cartItem);
        eventPublisher.accept(ItemAddedToCartEvent.of(getId(), cartItem));
    }

    @Override
    public CartItem removeItem(CartItemId itemId, EventPublisher eventPublisher) {
        return state.getItem(itemId).map(cartItem -> {
            eventPublisher.accept(ItemRemovedFromCartEvent.of(getId(), itemId));
            return cartItem;
        })
        .orElseThrow(() -> new RuntimeException(String.format("Item {} of cart {} not found", itemId, getId())));
    }

    public void created(CartCreatedEvent event) {
        LOG.debug("Cart received event: {}", event);
        audit.add(event);
        id = event.getCartId();
        state = CartMutableSample.of(event.getCartState());
    }

    public void itemAdded(ItemAddedToCartEvent event) {
        LOG.debug("Cart {} received event: {}", getId(), event);
        audit.add(event);
        state.add(event.getCartItem());
    }

    public void itemRemoved(ItemRemovedFromCartEvent event) {
        LOG.debug("Cart {} received event: {}", getId(), event);
        audit.add(event);
        state.remove(event.getCartItemId());
    }

    public void ordered(CartOrderedEvent event) {
        LOG.debug("Cart {} received event: {}", getId(), event);
        audit.add(event);
        state.setOrderId(event.getOrderId());
    }

    public void invoiced(CartInvoicedEvent event) {
        LOG.debug("Cart {} received event: {}", getId(), event);
        audit.add(event);
        state.setInvoiceId(event.getInvoiceId());
    }
}
