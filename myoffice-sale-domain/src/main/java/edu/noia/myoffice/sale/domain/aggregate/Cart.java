package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.common.util.validation.BeanValidator;
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
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.itemNotFound;
import static edu.noia.myoffice.common.util.validation.Rule.condition;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Cart extends BaseEntity<Cart, CartId, CartState> {

    protected Cart(CartState state) {
        super(CartId.random(), state);
    }

    public static Cart create(CartSpecification specification, EventPublisher eventPublisher) {
        Cart cart = new Cart(CartSample.from(validateBean(specification)));
        eventPublisher.publish(CartCreatedEventPayload.of(cart.getId(), specification));
        return cart;
    }

    protected static <T> T validateBean(T state) {
        return BeanValidator.validate(state);
    }

    public CartType getType() {
        return state.getType();
    }

    @Override
    protected CartState cloneState() {
        return CartSample.from(state);
    }

    public Amount getTotal() {
        return state.getItems().stream()
                .map(item -> item.getArticle().getTariff().apply(item.getQuantity()))
                .reduce(Amount::plus)
                .orElse(Amount.ZERO);
    }

    @Override
    public Cart modify(CartState modifier) {
        return this;
    }

    @Override
    public Cart patch(CartState modifier) {
        return this;
    }

    @Override
    public void validate(CartState state) {
        validateBean(state);
    }

    public void addItem(CartItem cartItem, EventPublisher eventPublisher) {
        condition(() -> state.getOrderId() == null, String.format("Cart {} has been ordered, no more add possible", getId()));
        validateBean(cartItem);
        eventPublisher.publish(ItemAddedToCartEventPayload.of(getId(), cartItem));
    }

    public CartItem removeItem(CartItemId itemId, EventPublisher eventPublisher) {
        condition(() -> state.getOrderId() == null, String.format("Cart {} has been ordered, no more add possible", getId()));
        return state.getItem(itemId).map(cartItem -> {
            eventPublisher.publish(ItemRemovedFromCartEventPayload.of(getId(), itemId));
            return cartItem;
        }).orElseThrow(itemNotFound(CartItem.class, itemId, Cart.class, getId()));
    }

    public void order(EventPublisher eventPublisher) {
        condition(() -> state.getOrderId() == null, String.format("Cart {} is already ordered", getId()));
        state.setOrderId(OrderId.random());
        eventPublisher.publish(CartOrderedEventPayload.of(getId(),
                state.getType(),
                state.getFolderId(),
                state.getOrderId(),
                getTotal()));
    }

    public void close(InvoiceId invoiceId, EventPublisher eventPublisher) {
        condition(() -> state.getOrderId() != null, String.format("Cart {} has not been ordered, hence not closeable", getId()));
        condition(() -> state.getInvoiceId() == null, String.format("Cart {} is already closed", getId()));
        state.setInvoiceId(invoiceId);
        eventPublisher.publish(CartInvoicedEventPayload.of(getId(), state.getInvoiceId()));
    }

    public Holder<Cart> save(CartRepository repository) {
        return repository.save(id, state);
    }

    protected void create(CartCreatedEventPayload event, Instant timestamp) {
        setId(event.getCartId());
        setState(CartSample.from(event.getCartSpecification()));
        andEvent(BaseEvent.of(event, timestamp));
    }

    protected void addItem(ItemAddedToCartEventPayload event, Instant timestamp) {
        state.add(event.getCartItem());
        andEvent(event, timestamp);
    }

    protected void removeItem(ItemRemovedFromCartEventPayload event, Instant timestamp) {
        state.remove(event.getCartItemId());
        andEvent(event, timestamp);
    }

    protected void order(CartOrderedEventPayload event, Instant timestamp) {
        state.setOrderId(event.getOrderId());
        andEvent(event, timestamp);
    }

    protected void invoice(CartInvoicedEventPayload event, Instant timestamp) {
        state.setInvoiceId(event.getInvoiceId());
        andEvent(event, timestamp);
    }
}
