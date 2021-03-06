package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.sale.domain.vo.*;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface CartState extends EntityState {

    @NotNull
    FolderId getFolderId();

    @NotNull
    CartType getType();

    String getTitle();

    String getNotes();

    default List<CartItem> getItems() {
        return Collections.emptyList();
    }

    default Optional<CartItem> getItem(CartItemId itemId) {
        return Optional.empty();
    }

    default OrderId getOrderId() {
        return null;
    }

    default InvoiceId getInvoiceId() {
        return null;
    }

    CartState add(CartItem... cartItems);

    Optional<CartItem> remove(CartItemId itemId);

    CartState setOrderId(OrderId orderId);

    CartState setInvoiceId(InvoiceId invoiceId);
}
