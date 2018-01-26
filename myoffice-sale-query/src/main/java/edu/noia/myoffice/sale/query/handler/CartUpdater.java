package edu.noia.myoffice.sale.query.handler;

import edu.noia.myoffice.sale.data.adapter.CartRepositoryAdapter;
import edu.noia.myoffice.sale.data.jpa.JpaCartState;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CartUpdater {

    @NonNull
    CartRepositoryAdapter repository;

    public void on(CartCreatedEvent event) {
        LOG.debug("{} received event: {}", getClass().getName(), event);
        repository.save(event.getCartState());
    }

    public void on(ItemAddedToCartEvent event) {
        LOG.debug("{} received event: {}", getClass().getName(), event);
        repository
                .findOne(event.getCartId())
                .ifPresent(cart -> ((JpaCartState)cart).add(event.getCartItem()));
    }

    public void on(ItemRemovedFromCartEvent event) {
        LOG.debug("{} received event: {}", getClass().getName(), event);
        repository
                .findOne(event.getCartId())
                .ifPresent(cart -> ((JpaCartState)cart).remove(event.getCartItemId()));
    }

    public void on(CartOrderedEvent event) {
        LOG.debug("{} received event: {}", getClass().getName(), event);
        repository
                .findOne(event.getCartId())
                .ifPresent(cart -> ((JpaCartState)cart).setOrderId(event.getOrderId()));
    }

    public void on(CartInvoicedEvent event) {
        LOG.debug("{} received event: {}", getClass().getName(), event);
        repository
                .findOne(event.getCartId())
                .ifPresent(cart -> ((JpaCartState)cart).setInvoiceId(event.getInvoiceId()));
    }
}
