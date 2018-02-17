package edu.noia.myoffice.sale.domain.event.invoice;

import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceCreatedEventPayload implements InvoiceEventPayload, CartEventPayload {
    @NonNull
    CartId cartId;
    @NonNull
    InvoiceId invoiceId;
}
