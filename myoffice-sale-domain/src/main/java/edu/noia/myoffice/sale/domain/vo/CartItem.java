package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.common.domain.vo.Amount;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = "id", callSuper = false, doNotUseGetters = true)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {

    CartItemId id = CartItemId.random();
    @NonNull
    Long quantity;
    @NonNull
    Article article;
    LocalDateTime timestamp;

    public Amount getPrice() {
        return article.getTariff().apply(quantity);
    }
}
