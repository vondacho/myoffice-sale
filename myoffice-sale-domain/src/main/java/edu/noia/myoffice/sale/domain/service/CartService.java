package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.aggregate.Cart;
import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.domain.event.item.ItemCreatedEvent;
import edu.noia.myoffice.sale.domain.repository.command.CartRepository;
import edu.noia.myoffice.sale.domain.vo.CartType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CartService {

    @NonNull
    CartRepository cartRepository;
    @NonNull
    EventPublisher eventPublisher;

    public void create(CreateCartCommand command) {
        LOG.debug("{} received command: {}", getClass(), command);
        Cart.of(command.getState(), eventPublisher).save(cartRepository);
    }

    public void addItem(AddItemToCartCommand command) {
        LOG.debug("{} received command: {}", getClass(), command);
        cartRepository.findOne(command.getCartId()).ifPresent(holder -> holder.execute(cart -> {
            if (cart.getType() == CartType.LOG) {
                eventPublisher.accept(ItemCreatedEvent.of(cart.getId(), command.getCartItem()));
            } else {
                cart.addItem(command.getCartItem(), eventPublisher);
            }
        }));
    }

    public void deposeItem(DeposeItemIntoCartCommand command) {
        LOG.debug("{} received command: {}", getClass(), command);
        cartRepository.findOne(command.getCartId()).ifPresent(holder -> holder.execute(cart -> {
            cart.addItem(command.getCartItem(), eventPublisher);
        }));
    }

    public void removeItem(RemoveItemFromCartCommand command) {
        LOG.debug("{} received command: {}", getClass(), command);
        cartRepository.findOne(command.getCartId()).ifPresent(holder -> holder.execute(cart -> {
            cart.removeItem(command.getCartItemId(), eventPublisher);
        }));
    }

    public void order(OrderCartCommand command) {
        LOG.debug("{} received command: {}", getClass(), command);
        cartRepository.findOne(command.getCartId()).ifPresent(holder -> holder.execute(cart -> {
            cart.order(eventPublisher);
        }));
    }

    public void close(CloseCartCommand command) {
        LOG.debug("{} received command: {}", getClass(), command);
        cartRepository.findOne(command.getCartId()).ifPresent(holder -> holder.execute(cart -> {
            cart.close(command.getInvoiceId(), eventPublisher);
        }));
    }

}
