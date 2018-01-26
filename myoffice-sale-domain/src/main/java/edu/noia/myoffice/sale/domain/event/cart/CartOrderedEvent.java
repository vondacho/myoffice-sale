package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import edu.noia.myoffice.sale.domain.vo.OrderId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartOrderedEvent extends BaseEvent implements CartEvent {
    @NonNull
    CartId cartId;
    @NonNull
    CartType cartType;
    @NonNull
    OrderId orderId;
}
