package edu.noia.myoffice.sale.command.handler.axon;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.domain.repository.CartRepository;
import edu.noia.myoffice.sale.domain.service.CartService;
import edu.noia.myoffice.sale.domain.vo.CartSample;
import org.axonframework.commandhandling.CommandHandler;

public class AxonCartServiceProxy extends CartService {

    public AxonCartServiceProxy(CartRepository cartRepository, EventPublisher eventPublisher) {
        super(cartRepository, eventPublisher);
    }

    @CommandHandler
    public void create(CreateCartCommand command) {
        cartRepository.save(null, CartSample.from(command.getSpecification()));
    }

    @CommandHandler
    public void addItem(AddItemToCartCommand command) {
        super.addItem(command);
    }

    @CommandHandler
    public void removeItem(RemoveItemFromCartCommand command) {
        super.removeItem(command);
    }

    @CommandHandler
    public void deposeItem(DeposeItemIntoCartCommand command) {
        super.deposeItem(command);
    }

    @CommandHandler
    public void order(OrderCartCommand command) {
        super.order(command);
    }

    @CommandHandler
    public void close(CloseCartCommand command) {
        super.close(command);
    }
}
