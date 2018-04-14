package edu.noia.myoffice.sale.command.command.axon;

import edu.noia.myoffice.sale.domain.command.CartCommandHandler;
import edu.noia.myoffice.sale.domain.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.CommandHandler;

/**
 * This class is a {@link CartCommand} listener which proxies a {@link CartCommandHandler} instance
 * Command listener aspect provided by Axon
 * Proxy pattern applied
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonCartCommandHandler implements CartCommandHandler {

    @NonNull
    CartCommandHandler commandHandler;

    @CommandHandler
    @Override
    public void create(CreateCartCommand command) {
        commandHandler.create(command);
    }

    @CommandHandler
    @Override
    public void addItem(AddItemToCartCommand command) {
        commandHandler.addItem(command);
    }

    @CommandHandler
    @Override
    public void removeItem(RemoveItemFromCartCommand command) {
        commandHandler.removeItem(command);
    }

    @CommandHandler
    @Override
    public void deposeItem(DeposeItemIntoCartCommand command) {
        commandHandler.deposeItem(command);
    }

    @CommandHandler
    @Override
    public void order(OrderCartCommand command) {
        commandHandler.order(command);
    }

    @CommandHandler
    @Override
    public void close(CloseCartCommand command) {
        commandHandler.close(command);
    }
}
