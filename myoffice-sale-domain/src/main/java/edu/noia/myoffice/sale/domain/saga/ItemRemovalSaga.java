package edu.noia.myoffice.sale.domain.saga;

import edu.noia.myoffice.sale.domain.event.article.SystemCancelledArticleReservationEventPayload;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEventPayload;

//TODO
public class ItemRemovalSaga {

    /**
     * Start of saga
     *
     * @param event
     */
    public void on(ItemRemovedFromCartEventPayload event) {
        throw new UnsupportedOperationException();
    }

    /**
     * End of saga
     *
     * @param event
     */
    public void on(SystemCancelledArticleReservationEventPayload event) {
        throw new UnsupportedOperationException();
    }
}
