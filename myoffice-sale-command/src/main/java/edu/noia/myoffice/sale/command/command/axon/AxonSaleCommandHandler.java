package edu.noia.myoffice.sale.command.command.axon;

import edu.noia.myoffice.sale.domain.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;
import edu.noia.myoffice.sale.domain.service.CartService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.CommandHandler;

/**
 * This class is a {@link CartCommand} listener which proxies a {@link CartService} instance
 * Command listener aspect provided by Axon
 * Proxy pattern applied
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AxonSaleCommandHandler implements CartService {

    @NonNull
    CartService cartService;

    @CommandHandler
    @Override
    public void create(CreateCartCommand command) {
        cartService.create(command);
    }

    @CommandHandler
    @Override
    public void addItem(AddItemToCartCommand command) {
        cartService.addItem(command);
    }

    @CommandHandler
    @Override
    public void removeItem(RemoveItemFromCartCommand command) {
        cartService.removeItem(command);
    }

    @CommandHandler
    @Override
    public void deposeItem(DeposeItemIntoCartCommand command) {
        cartService.deposeItem(command);
    }

    @CommandHandler
    @Override
    public void order(OrderCartCommand command) {
        cartService.order(command);
    }

    @CommandHandler
    @Override
    public void close(CloseCartCommand command) {
        cartService.close(command);
    }
}
