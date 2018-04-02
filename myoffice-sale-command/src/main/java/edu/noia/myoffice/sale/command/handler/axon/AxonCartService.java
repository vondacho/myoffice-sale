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

public class AxonCartService extends CartService {

    public AxonCartService(CartRepository cartRepository, EventPublisher eventPublisher) {
        super(cartRepository, eventPublisher);
    }

    @CommandHandler
    @Override
    public void create(CreateCartCommand command) {
        cartRepository.save(null, CartSample.from(command.getSpecification()));
    }

    @CommandHandler
    @Override
    public void addItem(AddItemToCartCommand command) {
        super.addItem(command);
    }

    @CommandHandler
    @Override
    public void removeItem(RemoveItemFromCartCommand command) {
        super.removeItem(command);
    }

    @CommandHandler
    @Override
    public void deposeItem(DeposeItemIntoCartCommand command) {
        super.deposeItem(command);
    }

    @CommandHandler
    @Override
    public void order(OrderCartCommand command) {
        super.order(command);
    }

    @CommandHandler
    @Override
    public void close(CloseCartCommand command) {
        super.close(command);
    }
}
