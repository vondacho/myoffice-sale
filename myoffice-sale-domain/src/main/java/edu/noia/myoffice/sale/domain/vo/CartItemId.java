package edu.noia.myoffice.sale.domain.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@ToString
@Getter
@EqualsAndHashCode(of="id", callSuper = false, doNotUseGetters = true)
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CartItemId {
    @NonNull
    UUID id;

    public static CartItemId random() {
        return new CartItemId(UUID.randomUUID());
    }
}
