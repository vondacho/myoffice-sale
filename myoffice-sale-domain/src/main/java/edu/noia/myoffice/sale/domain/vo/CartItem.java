package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.sale.domain.aggregate.Cart;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode(of = {"id"}, callSuper = false, doNotUseGetters = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CartItem {

    @NonNull
    UUID id;
    @NonNull
    Long quantity;
    @NonNull
    Article article;
    InvoiceId invoiceId;

    public CartItem invoice(InvoiceId invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }
}
