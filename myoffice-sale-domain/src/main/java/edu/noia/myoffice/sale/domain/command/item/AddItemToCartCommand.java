package edu.noia.myoffice.sale.domain.command.item;

import edu.noia.myoffice.sale.domain.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.vo.CartId;
import edu.noia.myoffice.sale.domain.vo.CartItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddItemToCartCommand implements CartCommand {
    @NonNull
    CartId cartId;
    @NonNull
    CartItem cartItem;
}
