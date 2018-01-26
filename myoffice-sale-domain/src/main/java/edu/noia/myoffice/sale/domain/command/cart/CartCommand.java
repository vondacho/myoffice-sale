package edu.noia.myoffice.sale.domain.command.cart;

import edu.noia.myoffice.common.domain.command.Command;
import edu.noia.myoffice.sale.domain.vo.CartId;

public interface CartCommand extends Command {

    CartId getCartId();
}
