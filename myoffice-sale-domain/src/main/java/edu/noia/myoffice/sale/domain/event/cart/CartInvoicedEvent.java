package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.event.invoice.InvoiceEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartInvoicedEvent extends BaseEvent implements CartEvent, InvoiceEvent {
    @NonNull
    CartId cartId;
    @NonNull
    InvoiceId invoiceId;
}
