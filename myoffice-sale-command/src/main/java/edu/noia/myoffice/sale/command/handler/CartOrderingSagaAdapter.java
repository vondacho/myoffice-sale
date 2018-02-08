package edu.noia.myoffice.sale.command.handler;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.sale.domain.event.article.ArticleListConfirmedEvent;
import edu.noia.myoffice.sale.domain.event.article.ArticleListReservedEvent;
import edu.noia.myoffice.sale.domain.event.article.SystemFailedToReserveArticleListEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.invoice.InvoiceCreatedEvent;
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
public class CartOrderingSagaAdapter extends CartOrderingSaga {

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
     * Start of saga
     * @param event
     */
    @StartSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(CartOrderedEvent event) {
        super.on(event, commandPublisher::accept);
    }

    /**
     * Continuation of saga
     * @param event
     */
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ArticleListReservedEvent event) {
        super.on(event, commandPublisher::accept);
    }

    /**
     * Continuation or end of saga
     * @param event
     */
    @SagaEventHandler(associationProperty = "cartId")
    public void on(ArticleListConfirmedEvent event) {
        super.on(event, commandPublisher::accept);
        if (getInvoiceId() != null) {
            end();
        }
    }

    /**
     * Continuation or end of saga
     * @param event
     */
    @SagaEventHandler(associationProperty = "cartId")
    public void on(InvoiceCreatedEvent event) {
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
    public void on(SystemFailedToReserveArticleListEvent event) {
        super.on(event, commandPublisher::accept);
    }

    /**
     * End of saga
     * @param event
     */
    @EndSaga
    @SagaEventHandler(associationProperty = "cartId")
    public void on(OrderCancelledEvent event) {
        super.on(event);
    }
}