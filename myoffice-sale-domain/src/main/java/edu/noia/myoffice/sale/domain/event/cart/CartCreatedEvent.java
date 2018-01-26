package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartSample;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartCreatedEvent extends BaseEvent implements CartEvent {
    @NonNull
    CartId cartId;
    @NonNull
    CartSample cartState;
}
