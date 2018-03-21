package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartSpecification;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartCreatedEventPayload implements CartEventPayload {
    @NonNull
    CartId cartId;
    @NonNull
    CartSpecification cartSpecification;
}
