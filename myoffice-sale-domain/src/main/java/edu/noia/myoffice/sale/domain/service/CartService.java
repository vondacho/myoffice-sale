package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.common.util.holder.Holder;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
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
public class CartService {

    @NonNull
    CartRepository cartRepository;
    @NonNull
    EventPublisher eventPublisher;

    public void create(CreateCartCommand command) {
        Cart.create(command.getSpecification(), eventPublisher).save(cartRepository);
    }

    public void addItem(AddItemToCartCommand command) {
        applyOnCart(command.getCartId(), cart -> {
            if (cart.getType() == CartType.LOG) {
                eventPublisher.publish(ItemCreatedEventPayload.of(cart.getId(), command.getCartItem()));
            } else {
                cart.addItem(command.getCartItem(), eventPublisher);
            }
        });
    }

    public void deposeItem(DeposeItemIntoCartCommand command) {
        applyOnCart(command.getCartId(), cart -> cart.addItem(command.getCartItem(), eventPublisher));
    }

    public void removeItem(RemoveItemFromCartCommand command) {
        applyOnCart(command.getCartId(), cart -> cart.removeItem(command.getCartItemId(), eventPublisher));
    }

    public void order(OrderCartCommand command) {
        applyOnCart(command.getCartId(), cart -> cart.order(eventPublisher));
    }

    public void close(CloseCartCommand command) {
        applyOnCart(command.getCartId(), cart -> cart.close(command.getInvoiceId(), eventPublisher));
    }

    private void applyOnCart(CartId cartId, Consumer<Cart> action) {
        findCart(cartId).execute(action::accept);
    }

    private Holder<Cart> findCart(CartId cartId) {
        return cartRepository.findOne(cartId).orElseThrow(notFound(Cart.class, cartId));
    }
}
