package edu.noia.myoffice.sale.query.listener;

import edu.noia.myoffice.sale.common.listener.CartEventListener;
import edu.noia.myoffice.sale.domain.aggregate.CartMutableState;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.query.repository.CartStateRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CartUpdater implements CartEventListener {

    @NonNull
    CartStateRepository repository;

    @Override
    public void created(CartCreatedEvent event) {
        repository.save(event.getCartId(), event.getCartState());
    }

    @Override
    public void itemAdded(ItemAddedToCartEvent event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> ((CartMutableState)cart).add(event.getCartItem()));
    }

    @Override
    public void itemRemoved(ItemRemovedFromCartEvent event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> ((CartMutableState)cart).remove(event.getCartItemId()));
    }

    @Override
    public void ordered(CartOrderedEvent event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> ((CartMutableState)cart).setOrderId(event.getOrderId()));
    }

    @Override
    public void invoiced(CartInvoicedEvent event) {
        repository
                .findById(event.getCartId())
                .ifPresent(cart -> ((CartMutableState)cart).setInvoiceId(event.getInvoiceId()));
    }
}
