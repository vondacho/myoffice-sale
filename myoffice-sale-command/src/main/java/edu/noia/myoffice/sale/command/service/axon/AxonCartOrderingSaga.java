package edu.noia.myoffice.sale.command.service.axon;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.sale.domain.event.article.ArticleListConfirmedEventPayload;
import edu.noia.myoffice.sale.domain.event.article.ArticleListReservedEventPayload;
import edu.noia.myoffice.sale.domain.event.article.SystemFailedToReserveArticleListEventPayload;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEventPayload;
import edu.noia.myoffice.sale.domain.event.invoice.InvoiceCreatedEventPayload;
import edu.noia.myoffice.sale.domain.event.order.OrderCancelledEvent;
import edu.noia.myoffice.sale.domain.service.CartOrderingSaga;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
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

import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

@Saga
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonCartOrderingSaga extends CartOrderingSaga {

    @Getter
    @Setter
    CartId cartId;
    @Getter
    @Setter
    FolderId folderId;
    @Getter
    @Setter
    InvoiceId invoiceId;
    @Getter
    @Setter
    Amount amount;
    @Getter
    @Setter
    boolean articleListConfirmed = false;

    transient CommandPublisher commandPublisher;

    @Autowired
    public void setCommandPublisher(CommandPublisher commandPublisher) {
        this.commandPublisher = commandPublisher;
    }

    /**
     * Start create saga
     * @param event
     */
    @StartSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(CartOrderedEventPayload event) {
        super.on(event, commandPublisher::accept);
    }

    /**
     * Continuation create saga
     * @param event
     */
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ArticleListReservedEventPayload event) {
        super.on(event, commandPublisher::accept);
    }

    /**
     * Continuation or end create saga
     * @param event
     */
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ArticleListConfirmedEventPayload event) {
        super.on(event, commandPublisher::accept);
        if (getInvoiceId() != null) {
            end();
        }
    }

    /**
     * Continuation or end create saga
     * @param event
     */
    @SagaEventHandler(associationProperty = "cartId")
    public void on(InvoiceCreatedEventPayload event) {
        super.on(event, commandPublisher::accept);
        if (isArticleListConfirmed()) {
            end();
        }
    }

    /**
     * Compensation
     * @param event
     */
//    @SagaEventHandler(associationProperty = "cartId")
    public void on(SystemFailedToReserveArticleListEventPayload event) {
        super.on(event, commandPublisher::accept);
    }

    /**
     * End create saga
     * @param event
     */
    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(OrderCancelledEvent event) {
        super.on(event);
    }
}
