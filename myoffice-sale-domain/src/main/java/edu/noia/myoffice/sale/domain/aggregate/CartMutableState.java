package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartItemId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import edu.noia.myoffice.sale.domain.vo.OrderId;

import java.util.Collection;
import java.util.Optional;

public interface CartMutableState extends CartState {

    CartMutableState add(CartItem item);

    default CartMutableState addAll(Collection<CartItem> items) {
        items.forEach(this::add);
        return this;
    }

    Optional<CartItem> remove(CartItemId itemId);

    OrderId getOrderId();

    CartMutableState setOrderId(OrderId orderId);

    InvoiceId getInvoiceId();

    CartMutableState setInvoiceId(InvoiceId invoiceId);
}
