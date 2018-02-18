package edu.noia.myoffice.sale.domain.event.cart;

import edu.noia.myoffice.common.domain.event.ProblemEventPayload;
import edu.noia.myoffice.common.util.exception.Problem;
import edu.noia.myoffice.sale.domain.vo.CartId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ToString
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartProblemEventPayload extends ProblemEventPayload {
    CartId cartId;

    public CartProblemEventPayload(@NonNull List<Problem> problems, @NonNull CartId cartId) {
        super(problems);
        this.cartId = cartId;
    }
}
