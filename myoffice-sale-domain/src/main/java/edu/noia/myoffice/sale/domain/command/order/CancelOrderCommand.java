package edu.noia.myoffice.sale.domain.command.order;

import edu.noia.myoffice.sale.domain.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelOrderCommand implements CartCommand {
    @NonNull
    CartId cartId;
}