package edu.noia.myoffice.sale.domain.event.article;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartEvent;
import edu.noia.myoffice.sale.domain.vo.ArticleId;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemFailedToReserveArticleEvent extends BaseEvent implements CartEvent, ArticleEvent {

    @NonNull
    CartId cartId;
    @NonNull
    ArticleId articleId;
    @NonNull
    Long quantity;
    @NonNull
    Long availableQuantity;
}
