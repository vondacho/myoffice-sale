package edu.noia.myoffice.sale.domain.aggregate;

import edu.noia.myoffice.sale.domain.vo.CartItem;
import edu.noia.myoffice.sale.domain.vo.CartItemId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import edu.noia.myoffice.sale.domain.vo.OrderId;

import java.util.Optional;

public interface MutableCartState extends CartState {

    MutableCartState add(CartItem... cartItems);

    Optional<CartItem> remove(CartItemId itemId);

    MutableCartState setOrderId(OrderId orderId);

    MutableCartState setInvoiceId(InvoiceId invoiceId);
}
