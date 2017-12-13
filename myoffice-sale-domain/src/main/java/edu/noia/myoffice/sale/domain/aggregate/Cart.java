package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.common.util.BeanValidator;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartSample;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class Cart extends BaseEntity<
        Cart,
        CartId,
        CartState,
        CartMutableState,
        CartRepository> {

    public static Cart ofValid(@NonNull CartState state) {
        return ofValid(CartId.random(), state);
    }

    public static Cart ofValid(@NonNull CartId id, @NonNull CartState state) {
        return new Cart().setState(state).setId(id);
    }

    public List<CartItem> getItems() { return unmodifiableList(state.getItems()); }

    public Cart add(CartItem item) {
        return setState(toMutable().add(item));
    }

    public Cart remove(CartItem item) {
        return setState(toMutable().remove(item).add(item));
    }

    public Cart invoice(InvoiceId invoiceId) {
        List<CartItem> items = new ArrayList(state.getItems());
        return setState(toMutable()
                .clear()
                .invoice(invoiceId)
                .add(items.stream()
                        .map(item -> item.invoice(invoiceId))
                        .collect(toList())));
    }

    public InvoiceId getInvoice() {
        return state.getInvoiceId();
    }

    @Override
    protected CartMutableState toMutableState(CartState state) {
        return null;
    }

    @Override
    protected CartState toImmutableState(CartState state) {
        return CartSample.of(state);
    }

    @Override
    protected CartId identify()  {
        return CartId.random();
    }

    @Override
    protected CartState validate(CartState state) {
        return validateState(state);
    }

    private static <T> T validateState(T state) {
        return BeanValidator.validate(state);
    }
}
