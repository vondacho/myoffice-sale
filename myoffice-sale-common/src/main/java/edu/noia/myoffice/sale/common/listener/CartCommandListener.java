package edu.noia.myoffice.sale.common.listener;

import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;

public interface CartCommandListener {

    void create(CreateCartCommand command);

    void addItem(AddItemToCartCommand command);

    void removeItem(RemoveItemFromCartCommand command);

    void deposeItem(DeposeItemIntoCartCommand command);

    void order(OrderCartCommand command);

    void close(CloseCartCommand command);
}
