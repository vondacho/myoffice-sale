package edu.noia.myoffice.sale.domain.event.item;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItemId;

public interface ItemEvent extends Event {

    CartItemId getCartItemId();
}
