package edu.noia.myoffice.sale.domain.event;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import lombok.Getter;

@Getter
public class CartItemRemoved extends BaseEvent {
    CartId cartId;
    CartItem item;
}
