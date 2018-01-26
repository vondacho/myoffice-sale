package edu.noia.myoffice.sale.domain.event.item;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCreatedEvent extends BaseEvent implements CartEvent {
    @NonNull
    CartId cartId;
    @NonNull
    CartItem cartItem;
}
