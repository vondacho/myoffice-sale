package edu.noia.myoffice.sale.command.handler;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.sale.domain.event.article.SystemFailedToReserveArticleEvent;
import edu.noia.myoffice.sale.domain.event.article.SystemReservedArticleEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemCreatedEvent;
import edu.noia.myoffice.sale.domain.service.ItemAdditionSaga;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemAdditionSagaAdapter extends ItemAdditionSaga {

    @Getter
    @Setter
    CartId cartId;
    @Getter
    @Setter
    CartItem cartItem;

    transient CommandPublisher commandPublisher;

    @Autowired
    public void setCommandPublisher(CommandPublisher commandPublisher) {
        this.commandPublisher = commandPublisher;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ItemCreatedEvent event) {
        super.on(event, commandPublisher::accept);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(SystemFailedToReserveArticleEvent event) {
        super.on(event);
    }

    @SagaEventHandler(associationProperty = "cartId")
    public void on(SystemReservedArticleEvent event) {
        super.on(event, commandPublisher::accept);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ItemAddedToCartEvent event) {
        super.on(event);
    }

}
