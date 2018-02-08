package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.common.domain.command.CommandPublisher;
import edu.noia.myoffice.sale.domain.command.article.ReserveArticleCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.event.article.SystemFailedToReserveArticleEvent;
import edu.noia.myoffice.sale.domain.event.article.SystemReservedArticleEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemAddedToCartEvent;
import edu.noia.myoffice.sale.domain.event.item.ItemCreatedEvent;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ItemAdditionSaga {

    /**
     * Start of saga
     *
     * @param event
     */
    public void on(ItemCreatedEvent event, CommandPublisher commandPublisher) {
        setCartId(event.getCartId());
        setCartItem(event.getCartItem());
        commandPublisher.accept(
                ReserveArticleCommand.of(
                    event.getCartId(),
                    event.getCartItem().getArticle().getArticleId(),
                    event.getCartItem().getQuantity()));
    }

    /**
     * Continuation of Saga
     *
     * @param event
     */
    public void on(SystemReservedArticleEvent event, CommandPublisher commandPublisher) {
        commandPublisher.accept(
                DeposeItemIntoCartCommand.of(
                        event.getCartId(),
                        getCartItem()));
    }

    /**
     * End of saga
     *
     * @param event
     */
    public void on(ItemAddedToCartEvent event) {
    }

    /**
     * End of saga
     *
     * @param event
     */
    public void on(SystemFailedToReserveArticleEvent event) {
    }

    protected abstract void setCartId(CartId cartId);
    protected abstract CartId getCartId();
    protected abstract void setCartItem(CartItem cartItem);
    protected abstract CartItem getCartItem();
}
