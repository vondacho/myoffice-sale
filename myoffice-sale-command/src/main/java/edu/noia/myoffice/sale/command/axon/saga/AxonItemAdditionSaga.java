package edu.noia.myoffice.sale.command.axon.saga;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.sale.domain.event.article.SystemFailedToReserveArticleEvent;
import edu.noia.myoffice.sale.domain.event.article.SystemReservedArticleEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemCreatedEvent;
import edu.noia.myoffice.sale.domain.service.saga.ItemAdditionSaga;
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

import javax.persistence.Transient;

@Saga
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonItemAdditionSaga extends ItemAdditionSaga {

    @Getter
    @Setter
    CartId cartId;
    @Getter
    @Setter
    CartItem cartItem;

    @Autowired
    @Transient
    CommandPublisher commandPublisher;

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
