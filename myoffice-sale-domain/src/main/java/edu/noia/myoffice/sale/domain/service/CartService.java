package edu.noia.myoffice.sale.domain.service;

import edu.noia.myoffice.sale.domain.command.cart.CloseCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.domain.command.cart.OrderCartCommand;
import edu.noia.myoffice.sale.domain.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.command.item.DeposeItemIntoCartCommand;
import edu.noia.myoffice.sale.domain.command.item.RemoveItemFromCartCommand;

public interface CartService {

    void create(CreateCartCommand command);

    void addItem(AddItemToCartCommand command);

    void deposeItem(DeposeItemIntoCartCommand command);

    void removeItem(RemoveItemFromCartCommand command);

    void order(OrderCartCommand command);

    void close(CloseCartCommand command);
}
