package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.util.BeanValidator;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.domain.repository.command.CartRepository;
import edu.noia.myoffice.sale.domain.util.Holder;
import edu.noia.myoffice.sale.domain.vo.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Cart {

    @Getter
    @Setter(value = AccessLevel.PRIVATE)
    CartId id;
    @NonNull
    CartMutableState state;

    public static Cart of(CartState cartState) {
        return new Cart(CartMutableSample.of(validate(cartState))).identify();
    }

    protected static <T> T validate(T state) {
        return BeanValidator.validate(state);
    }

    public CartType getType() {
        return state.getType();
    }

    public CartState getState() {
        return CartSample.of(state);
    }

    protected Cart identify() {
        return setId(CartId.random());
    }

    public void addItem(CartItem cartItem, EventPublisher eventPublisher) {
        if (state.getOrderId() != null) {
            throw new RuntimeException(String.format("Cart {} has been ordered, no more add possible", getId()));
        }
        state.add(validate(cartItem));
        eventPublisher.accept(ItemAddedToCartEvent.of(getId(), cartItem));
    }

    public CartItem removeItem(CartItemId itemId, EventPublisher eventPublisher) {
        if (state.getOrderId() != null) {
            throw new RuntimeException(String.format("Cart {} has been ordered, no more add possible", getId()));
        }
        return state.remove(itemId).map(cartItem -> {
            eventPublisher.accept(ItemRemovedFromCartEvent.of(getId(), itemId));
            return cartItem;
        })
        .orElseThrow(() -> new RuntimeException(String.format("Item {} of cart {} not found", itemId, getId())));
    }

    public void order(EventPublisher eventPublisher) {
        if (state.getOrderId() != null) {
            throw new RuntimeException(String.format("Cart {} is already ordered", getId()));
        }
        state.setOrderId(OrderId.random());
        eventPublisher.accept(CartOrderedEvent.of(getId(), state.getType(), state.getOrderId()));
    }

    public void close(InvoiceId invoiceId, EventPublisher eventPublisher) {
        if (state.getOrderId() != null) {
            if (state.getInvoiceId() != null) {
                throw new RuntimeException(String.format("Cart {} is already closed", getId()));
            }
            state.setInvoiceId(invoiceId);
            eventPublisher.accept(CartInvoicedEvent.of(getId(), state.getInvoiceId()));
        }
        else throw new RuntimeException(String.format("Cart {} has not been ordered, hence not closeable", getId()));
    }

    public Holder<Cart> save(CartRepository repository) {
        return repository.save(id, state);
    }
}
