package edu.noia.myoffice.sale.domain.vo;

import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Quantity;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Accessors(chain = true)
@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false, doNotUseGetters = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {

    CartItemId id;
    @NonNull
    Article article;
    @NonNull
    Quantity quantity;
    @Setter
    LocalDateTime timestamp;

    private CartItem() {
        id = CartItemId.random();
        timestamp = LocalDateTime.now();
    }

    public static CartItem of(@NonNull CartItemId id, @NonNull Article article, @NonNull Quantity quantity, LocalDateTime timestamp) {
        if (article.getTariff().getTargetUnit().compatibleTo(quantity.getUnit())) {
            return new CartItem(article, quantity)
                    .setTimestamp(timestamp)
                    .setId(id);
        }
        throw new IllegalArgumentException(String.format("incompatible operand type; actual is %s, expected is %s",
                quantity.getUnit().getFamily(), article.getTariff().getTargetUnit().getFamily()));
    }

    public Amount getPrice() {
        return article.getTariff().apply(quantity);
    }
}
