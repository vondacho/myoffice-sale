package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.sale.domain.command.article.ConfirmArticleListCommand;
import edu.noia.myoffice.sale.domain.command.article.ReserveArticleListCommand;
import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.InvoiceCartCommand;
import edu.noia.myoffice.sale.domain.command.order.CancelOrderCommand;
import edu.noia.myoffice.sale.domain.event.article.ArticleListConfirmedEvent;
import edu.noia.myoffice.sale.domain.event.article.ArticleListReservedEvent;
import edu.noia.myoffice.sale.domain.event.article.SystemFailedToReserveArticleListEvent;
import edu.noia.myoffice.sale.domain.event.cart.CartOrderedEvent;
import edu.noia.myoffice.sale.domain.event.invoice.InvoiceCreatedEvent;
import edu.noia.myoffice.sale.domain.event.order.OrderCancelledEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartType;
import edu.noia.myoffice.sale.domain.vo.FolderId;
import edu.noia.myoffice.sale.domain.vo.InvoiceId;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
public abstract class CartOrderingSaga {

    /**
     * Start of saga
     * @param event
     */
    public void on(CartOrderedEvent event,CommandPublisher commandPublisher) {
        setFolderId(event.getFolderId());
        setCartId(event.getCartId());
        setAmount(event.getAmount());
        if (event.getCartType() == CartType.LOG) {
            commandPublisher.accept(ConfirmArticleListCommand.of(event.getCartId(), new HashMap<>()));
            commandPublisher.accept(InvoiceCartCommand.of(event.getCartId(), event.getFolderId(), event.getAmount()));
        }
        else {
            commandPublisher.accept(ReserveArticleListCommand.of(event.getCartId(), new HashMap<>()));
        }
    }

    /**
     * Continuation of saga
     * @param event
     */
    public void on(ArticleListReservedEvent event, CommandPublisher commandPublisher) {
        commandPublisher.accept(ConfirmArticleListCommand.of(getCartId(), new HashMap<>()));
        commandPublisher.accept(InvoiceCartCommand.of(getCartId(), getFolderId(), getAmount()));
    }

    /**
     * Continuation or end of saga
     * @param event
     */
    public void on(ArticleListConfirmedEvent event, CommandPublisher commandPublisher) {
        setArticleListConfirmed(true);
        if (getInvoiceId() != null) {
            commandPublisher.accept(CloseCartCommand.of(getCartId(), getInvoiceId()));
        }
    }

    /**
     * Continuation or end of saga
     * @param event
     */
    public void on(InvoiceCreatedEvent event, CommandPublisher commandPublisher) {
        setInvoiceId(event.getInvoiceId());
        if (isArticleListConfirmed()) {
            commandPublisher.accept(CloseCartCommand.of(getCartId(), event.getInvoiceId()));
        }
    }

    /**
     * Compensation
     * @param event
     */
    public void on(SystemFailedToReserveArticleListEvent event, CommandPublisher commandPublisher) {
        commandPublisher.accept(CancelOrderCommand.of(getCartId()));
    }

    /**
     * End of saga
     * @param event
     */
    public void on(OrderCancelledEvent event) {
    }

    protected abstract void setFolderId(FolderId folderId);
    protected abstract FolderId getFolderId();
    protected abstract void setCartId(CartId cartId);
    protected abstract CartId getCartId();
    protected abstract void setAmount(Amount amount);
    protected abstract Amount getAmount();
    protected abstract void setInvoiceId(InvoiceId invoiceId);
    protected abstract InvoiceId getInvoiceId();
    protected abstract void setArticleListConfirmed(boolean confirmed);
    protected abstract boolean isArticleListConfirmed();
}
