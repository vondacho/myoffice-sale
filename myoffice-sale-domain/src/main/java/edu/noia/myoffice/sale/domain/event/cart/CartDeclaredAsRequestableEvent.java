package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDeclaredAsRequestableEvent extends BaseEvent implements CartEvent {
    @NonNull
    CartId cartId;
}
