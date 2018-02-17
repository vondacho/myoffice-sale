package edu.noia.myoffice.sale.query.handler;

import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CartUpdater {

    @NonNull
    CartStateRepository repository;

    public void created(CartCreatedEventPayload event) {
        repository.save(event.getCartId(), event.getCartState());
    }

    public void itemAdded(ItemAddedToCartEventPayload event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> ((CartMutableState)cart).add(event.getCartItem()));
    }

    public void itemRemoved(ItemRemovedFromCartEventPayload event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> ((CartMutableState)cart).remove(event.getCartItemId()));
    }

    public void ordered(CartOrderedEventPayload event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> ((CartMutableState)cart).setOrderId(event.getOrderId()));
    }

    public void invoiced(CartInvoicedEventPayload event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> ((CartMutableState)cart).setInvoiceId(event.getInvoiceId()));
    }
}