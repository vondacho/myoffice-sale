package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityMutableState;
import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;

import java.util.Collection;

public interface CartMutableState<T extends CartItem>
        extends CartState<T>, EntityMutableState<CartMutableState, CartState> {

    CartMutableState setTitle(String value);
    CartMutableState setNotes(String value);

    default CartMutableState modify(CartState modifier) {
        return setTitle(modifier.getTitle())
                .setNotes(modifier.getNotes());
    }
    default CartMutableState patch(CartState modifier) {
        return setTitle(modifier.getTitle() != null ? modifier.getTitle() : getTitle())
                .setNotes(modifier.getNotes() != null ? modifier.getNotes() : getNotes());
    }

    default CartMutableState add(T item) {
        getItems().add(item);
        return this;
    }

    default CartMutableState add(Collection<T> items) {
        getItems().addAll(items);
        return this;
    }

    default CartMutableState remove(T item) {
        if (!getItems().remove(item)) {
            throw new ResourceNotFoundException(
                String.format("No %s identified by %s has been found", CartItem.class, item.getId()));
        }
        return this;
    }

    default CartMutableState clear() {
        getItems().clear();
        return this;
    }

    CartMutableState invoice(InvoiceId invoiceId);
}
