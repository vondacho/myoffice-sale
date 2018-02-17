package edu.noia.myoffice.sale.domain.event.item;

import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItemId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemAvailabilityDismissedEventPayload implements CartEventPayload, ItemEventPayload {
    @NonNull
    CartId cartId;
    @NonNull
    CartItemId cartItemId;
    @NonNull
    Long availableQuantity;
}
