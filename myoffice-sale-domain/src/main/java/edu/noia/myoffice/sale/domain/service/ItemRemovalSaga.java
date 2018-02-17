package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.sale.domain.event.article.SystemCancelledArticleReservationEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ItemRemovalSaga {

    /**
     * Start of saga
     * @param event
     */
    public void on(ItemRemovedFromCartEventPayload event) {
    }

    /**
     * End of saga
     * @param event
     */
    public void on(SystemCancelledArticleReservationEventPayload event) {
    }
}
