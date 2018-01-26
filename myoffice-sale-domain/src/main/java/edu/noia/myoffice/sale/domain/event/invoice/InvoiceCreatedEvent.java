package edu.noia.myoffice.sale.domain.event.invoice;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceCreatedEvent extends BaseEvent implements InvoiceEvent, CartEvent {
    @NonNull
    CartId cartId;
    @NonNull
    InvoiceId invoiceId;
}
