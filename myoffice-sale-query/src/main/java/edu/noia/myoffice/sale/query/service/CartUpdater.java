package edu.noia.myoffice.sale.query.service;

import edu.noia.myoffice.sale.domain.event.CartEventHandler;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;
import edu.noia.myoffice.sale.domain.vo.CartSample;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartUpdater implements CartEventHandler {

    @NonNull
    CartStateRepository repository;

    @Override
    public void created(CartCreatedEventPayload event) {
        repository.save(event.getCartId(), CartSample.from(event.getCartSpecification()));
    }

    @Override
    public void itemAdded(ItemAddedToCartEventPayload event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> cart.add(event.getCartItem()));
    }

    @Override
    public void itemRemoved(ItemRemovedFromCartEventPayload event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> cart.remove(event.getCartItemId()));
    }

    @Override
    public void ordered(CartOrderedEventPayload event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> cart.setOrderId(event.getOrderId()));
    }

    @Override
    public void invoiced(CartInvoicedEventPayload event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> cart.setInvoiceId(event.getInvoiceId()));
    }
}
