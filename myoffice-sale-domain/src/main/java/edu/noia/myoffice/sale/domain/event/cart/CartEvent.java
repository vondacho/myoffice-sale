package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.domain.vo.CartId;

public interface CartEvent extends Event {

    CartId getCartId();
}
