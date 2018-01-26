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
public final class CartId {
    @NonNull
    UUID id;

    public static CartId random() {
        return new CartId(UUID.randomUUID());
    }
}
