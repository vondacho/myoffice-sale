package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.command.CartCommandHandler;
import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.domain.event.item.ItemCreatedEventPayload;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.function.Consumer;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.notFound;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CartService implements CartCommandHandler {

    @NonNull
    CartRepository cartRepository;
    @NonNull
    EventPublisher eventPublisher;

    public void create(CreateCartCommand command) {
        Cart.create(command.getSpecification(), eventPublisher::publish).save(cartRepository);
    }

    public void addItem(AddItemToCartCommand command) {
        applyOn(command.getCartId(), cart -> {
            if (cart.getType() == CartType.LOG) {
                eventPublisher.publish(ItemCreatedEventPayload.of(cart.getId(), command.getCartItem()));
            } else {
                cart.addItem(command.getCartItem(), eventPublisher::publish);
            }
        });
    }

    public void deposeItem(DeposeItemIntoCartCommand command) {
        applyOn(command.getCartId(), cart -> cart.addItem(command.getCartItem(), eventPublisher::publish));
    }

    public void removeItem(RemoveItemFromCartCommand command) {
        applyOn(command.getCartId(), cart -> cart.removeItem(command.getCartItemId(), eventPublisher::publish));
    }

    public void order(OrderCartCommand command) {
        applyOn(command.getCartId(), cart -> cart.order(eventPublisher::publish));
    }

    public void close(CloseCartCommand command) {
        applyOn(command.getCartId(), cart -> cart.close(command.getInvoiceId(), eventPublisher::publish));
    }

    private void applyOn(CartId cartId, Consumer<Cart> action) {
        find(cartId).execute(action);
    }

    private Holder<Cart> find(CartId cartId) {
        return cartRepository.findOne(cartId).orElseThrow(notFound(Cart.class, cartId));
    }
}
