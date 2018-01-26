package edu.noia.myoffice.sale.domain.event.article;

import edu.noia.myoffice.sale.domain.event.cart.CartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemEvent;

public interface SystemCancelledArticleReservationEvent extends CartEvent, ItemEvent, ArticleEvent {
}
