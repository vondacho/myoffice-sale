package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.sale.domain.event.article.SystemCancelledArticleReservationEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ItemRemovalSaga {

    /**
     * Start of saga
     * @param event
     */
    public void on(ItemRemovedFromCartEvent event) {
    }

    /**
     * End of saga
     * @param event
     */
    public void on(SystemCancelledArticleReservationEvent event) {
    }
}
