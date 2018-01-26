package edu.noia.myoffice.sale.domain.command.cart;

import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CloseCartCommand implements CartCommand {
    @NonNull
    CartId cartId;
    @NonNull
    InvoiceId invoiceId;
}
