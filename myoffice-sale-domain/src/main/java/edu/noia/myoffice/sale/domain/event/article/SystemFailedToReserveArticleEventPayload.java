package edu.noia.myoffice.sale.domain.event.article;

import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.vo.ArticleId;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SystemFailedToReserveArticleEventPayload implements CartEventPayload, ArticleEventPayload {

    @NonNull
    CartId cartId;
    @NonNull
    ArticleId articleId;
    @NonNull
    Long quantity;
    @NonNull
    Long availableQuantity;
}
