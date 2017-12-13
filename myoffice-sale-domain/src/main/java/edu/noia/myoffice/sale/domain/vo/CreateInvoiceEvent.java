package edu.noia.myoffice.sale.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateInvoiceEvent {
    @NonNull
    CartId cartId;
    @NonNull
    InvoiceId invoiceId;
}
