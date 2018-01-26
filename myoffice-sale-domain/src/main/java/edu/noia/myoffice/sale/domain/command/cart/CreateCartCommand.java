package edu.noia.myoffice.sale.domain.command.cart;

import edu.noia.myoffice.common.domain.command.Command;
import edu.noia.myoffice.sale.domain.aggregate.CartState;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCartCommand implements Command {
    @NonNull
    CartState state;
}
