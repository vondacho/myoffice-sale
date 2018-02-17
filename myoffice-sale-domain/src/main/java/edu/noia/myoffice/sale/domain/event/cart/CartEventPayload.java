package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.sale.domain.vo.CartId;

public interface CartEventPayload extends EventPayload {

    CartId getCartId();
}
