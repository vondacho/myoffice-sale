package edu.noia.myoffice.sale.domain.event.article;

import edu.noia.myoffice.sale.domain.event.cart.CartEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemEventPayload;

public interface SystemCancelledArticleReservationEventPayload extends CartEventPayload, ItemEventPayload, ArticleEventPayload {
}
