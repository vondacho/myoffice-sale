package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.domain.util.EntityAudit;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.util.BeanValidator;
import edu.noia.myoffice.common.util.Holder;
import edu.noia.myoffice.sale.domain.event.cart.CartCreatedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartInvoicedEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.domain.repository.command.CartRepository;
import edu.noia.myoffice.sale.domain.vo.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static edu.noia.myoffice.common.domain.util.Rule.condition;
import static edu.noia.myoffice.common.domain.util.Rule.violation;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

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

    EntityAudit audit;

    public static Cart of(CartState state, EventPublisher eventPublisher) {
        Cart cart = new Cart(CartMutableSample.of(validate(state))).identify();
        eventPublisher.accept(CartCreatedEvent.of(cart.getId(), CartSample.of(state)));
        return cart;
    }

    private static <T> T validate(T bean) {
        return BeanValidator.validate(bean);
    }

    protected Cart identify() {
        return setId(CartId.random());
    }

    public CartType getType() {
        return state.getType();
    }

    public CartState getState() {
        return CartSample.of(state);
    }

    public List<Event> getAudit() {
        return audit != null ? emptyList() : unmodifiableList(audit.getEvents());
    }

    public Amount getTotal() {
        return state.getItems().stream()
                .map(item -> item.getArticle().getTariff().apply(item.getQuantity()))
                .reduce(Amount::plus)
                .orElse(Amount.ZERO);
    }

    public void addItem(CartItem cartItem, EventPublisher eventPublisher) {
        condition(() -> state.getOrderId() == null, String.format("Cart {} has been ordered, no more add possible", getId()));
        validate(cartItem);
        eventPublisher.accept(ItemAddedToCartEvent.of(getId(), cartItem));
    }

    public CartItem removeItem(CartItemId itemId, EventPublisher eventPublisher) {
        condition(() -> state.getOrderId() == null, String.format("Cart {} has been ordered, no more add possible", getId()));
        return state.getItem(itemId).map(cartItem -> {
            eventPublisher.accept(ItemRemovedFromCartEvent.of(getId(), itemId));
            return cartItem;
        }).orElseThrow(violation(String.format("Item {} of cart {} not found", itemId, getId())));
    }

    public void order(EventPublisher eventPublisher) {
        condition(() -> state.getOrderId() == null, String.format("Cart {} is already ordered", getId()));
        state.setOrderId(OrderId.random());
        eventPublisher.accept(CartOrderedEvent.of(getId(), state.getType(), state.getFolderId(), state.getOrderId(), getTotal()));
    }

    public void close(InvoiceId invoiceId, EventPublisher eventPublisher) {
        condition(() -> state.getOrderId() != null, String.format("Cart {} has not been ordered, hence not closeable", getId()));
        condition(() -> state.getInvoiceId() == null, String.format("Cart {} is already closed", getId()));
        state.setInvoiceId(invoiceId);
        eventPublisher.accept(CartInvoicedEvent.of(getId(), state.getInvoiceId()));
    }

    public Holder<Cart> save(CartRepository repository) {
        return repository.save(id, state);
    }

    protected void create(CartCreatedEvent event) {
        audit.audit(event);
        id = event.getCartId();
        state = CartMutableSample.of(event.getCartState());
    }

    protected void addItem(ItemAddedToCartEvent event) {
        audit.audit(event);
        state.add(event.getCartItem());
    }

    protected void removeItem(ItemRemovedFromCartEvent event) {
        audit.audit(event);
        state.remove(event.getCartItemId());
    }

    protected void order(CartOrderedEvent event) {
        audit.audit(event);
        state.setOrderId(event.getOrderId());
    }

    protected void invoice(CartInvoicedEvent event) {
        audit.audit(event);
        state.setInvoiceId(event.getInvoiceId());
    }
}
