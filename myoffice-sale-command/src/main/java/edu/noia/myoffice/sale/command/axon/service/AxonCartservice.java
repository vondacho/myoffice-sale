package edu.noia.myoffice.sale.command.axon.service;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.domain.repository.command.CartRepository;
import edu.noia.myoffice.sale.domain.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AxonCartservice extends CartService {

    public AxonCartservice(CartRepository cartRepository, EventPublisher eventPublisher) {
        super(cartRepository, eventPublisher);
    }

    @CommandHandler
    public void create(CreateCartCommand command) {
        LOG.debug("{} received command: {}", getClass(), command);
        cartRepository.save(null, command.getState());
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
