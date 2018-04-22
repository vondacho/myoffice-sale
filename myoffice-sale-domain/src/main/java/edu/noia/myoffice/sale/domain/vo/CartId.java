package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.common.domain.vo.Identity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode(of = "id", callSuper = false, doNotUseGetters = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CartId implements Identity {
    @NonNull
    UUID id;

    public static CartId random() {
        return new CartId(UUID.randomUUID());
    }

    public String toString() {
        return id.toString();
    }
}
