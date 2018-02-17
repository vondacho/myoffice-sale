package edu.noia.myoffice.sale.domain.event.item;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.sale.domain.vo.CartItemId;

public interface ItemEventPayload extends EventPayload {

    CartItemId getCartItemId();
}
