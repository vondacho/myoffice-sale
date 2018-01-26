package edu.noia.myoffice.sale.command.axon.saga;

import edu.noia.myoffice.sale.domain.event.article.SystemCancelledArticleReservationEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemRemovedFromCartEvent;
import edu.noia.myoffice.sale.domain.service.saga.ItemRemovalSaga;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItemId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonItemRemovalSaga extends ItemRemovalSaga {

    @Getter
    @Setter
    CartId cartId;
    @Getter
    @Setter
    CartItemId cartItemId;

    /**
     * Start of saga
     * @param event
     */
    @StartSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ItemRemovedFromCartEvent event) {
        super.on(event);
    }

    /**
     * End of saga
     * @param event
     */
    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(SystemCancelledArticleReservationEvent event) {
        super.on(event);
    }
}
